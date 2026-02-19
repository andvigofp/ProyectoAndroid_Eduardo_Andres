package com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio

import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.PerfilUsuarioApiService
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Esta clase gestiona la obtención y actualización del perfil del usuario.
 * Primero intenta obtener o actualizar los datos desde la API remota.
 * Si falla la red, utiliza la base de datos local Room como fallback.
 *
 * @author Andrés
 * @see Implementación híbrida (Retrofit + Room) del repositorio de Perfil de Usuario
 * @param api Servicio Retrofit encargado de las operaciones remotas del perfil.
 * @param userDao DAO de Room para acceder y persistir datos del usuario localmente.
 */
class PerfilUsuarioRepositoryRoom(
    private val api: PerfilUsuarioApiService,
    private val userDao: UserDao
) : IPerfilUsuarioRepository {

    /**
     * Obtiene un usuario por su identificador.
     *
     * Primero intenta obtenerlo desde la API remota.
     * Si la petición es exitosa, guarda el resultado en Room.
     * Si falla la red o no se encuentra en remoto, intenta recuperarlo desde Room.
     *
     * @param id Identificador único del usuario.
     * @param onError Callback que se ejecuta si el usuario no se encuentra
     * o si ocurre un error.
     * @param onSuccess Callback que devuelve el usuario encontrado como UserDTO.
     */
    override fun getUsuarioPorId(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Intento remoto
                val response = api.obtenerPerfilUsuario()

                if (response.isSuccessful && response.body() != null) {

                    val users = response.body()!!
                    val found = users.firstOrNull { it.id == id }

                    if (found != null) {

                        val entity = User(
                            id = found.id,
                            name = found.name,
                            email = found.email,
                            passwd = found.passwd,
                            keepLogged = found.keepLogged
                        )

                        // Guardamos en Room
                        userDao.insert(entity)

                        val dto = UserDTO(
                            id = found.id,
                            name = found.name,
                            email = found.email,
                            password = found.passwd,
                            keepLogged = found.keepLogged
                        )

                        withContext(Dispatchers.Main) { onSuccess(dto) }
                        return@launch
                    }
                }

            } catch (_: Exception) {
                // Si falla red → seguimos offline
            }

            // Fallback Room
            val local = userDao.getById(id)

            if (local != null) {

                val dto = UserDTO(
                    id = local.id,
                    name = local.name,
                    email = local.email,
                    password = local.passwd,
                    keepLogged = local.keepLogged
                )

                withContext(Dispatchers.Main) { onSuccess(dto) }

            } else {
                withContext(Dispatchers.Main) {
                    onError(Throwable("Usuario no encontrado"))
                }
            }
        }
    }

    /**
     * Actualiza los datos de un usuario.
     *
     * Primero intenta realizar la actualización en la API remota.
     * Si la operación remota es exitosa, sincroniza los datos en Room.
     * Si falla la red, realiza la actualización únicamente en Room (modo offline).
     *
     * @param usuario Objeto UserDTO con los datos actualizados.
     * @param onError Callback que se ejecuta si ocurre un error inesperado.
     * @param onSuccess Callback que se ejecuta cuando la actualización finaliza correctamente.
     */
    override fun actualizarUsuario(
        usuario: UserDTO,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Intento remoto
                val updated = UsuarioDto(
                    id = usuario.id.toString(),
                    name = usuario.name,
                    email = usuario.email,
                    passwd = usuario.password,
                    keepLogged = usuario.keepLogged
                )

                val response = api.actualizarUsuario(usuario.id.toString(), updated)

                if (response.isSuccessful) {

                    // Actualizamos Room
                    userDao.insert(
                        User(
                            id = usuario.id.toString(),
                            name = usuario.name,
                            email = usuario.email,
                            passwd = usuario.password,
                            keepLogged = usuario.keepLogged
                        )
                    )

                    withContext(Dispatchers.Main) { onSuccess() }
                    return@launch
                }

            } catch (_: Exception) {
                // Si falla red → seguimos offline
            }

            // Offline update
            userDao.insert(
                User(
                    id = usuario.id.toString(),
                    name = usuario.name,
                    email = usuario.email,
                    passwd = usuario.password,
                    keepLogged = usuario.keepLogged
                )
            )

            withContext(Dispatchers.Main) { onSuccess() }
        }
    }
}