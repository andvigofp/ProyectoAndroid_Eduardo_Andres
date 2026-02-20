package com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio

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

                    withContext(Dispatchers.Main) { onSuccess() }
                    return@launch
                }

            } catch (_: Exception) {
                // Si falla red → seguimos offline
            }

            withContext(Dispatchers.Main) { onSuccess() }
        }
    }
}