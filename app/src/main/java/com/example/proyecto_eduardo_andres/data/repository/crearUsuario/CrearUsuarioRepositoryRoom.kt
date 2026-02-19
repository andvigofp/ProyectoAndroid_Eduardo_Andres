package com.example.proyecto_eduardo_andres.data.repository.crearUsuario

import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.dto.RegisterDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

/**
 * Implementación del repositorio de creación de usuario utilizando
 * Retrofit + Room (modo híbrido online/offline).
 *
 * Esta clase intenta registrar el usuario primero en el servidor remoto.
 * Si la petición es exitosa, guarda el usuario en la base de datos local Room.
 *
 * En caso de fallo de red, permite la creación offline generando un ID local
 * y almacenando el usuario directamente en Room.
 *
 * @property authApi Servicio Retrofit encargado de registrar usuarios en el backend.
 * @property userDao DAO de Room utilizado para persistir el usuario localmente.
 *
 * @author Eduardo
 * @see ICrearUsuarioRepository
 * @see AuthApiService
 * @see UserDao
 * @see User
 * @see UserDTO
 */
class CrearUsuarioRepositoryRoom(
    private val authApi: AuthApiService,
    private val userDao: UserDao
) : ICrearUsuarioRepository {

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * Flujo de ejecución:
     * 1. Intenta registrar el usuario en el servidor mediante Retrofit.
     * 2. Si la respuesta es correcta, guarda el usuario en Room.
     * 3. Si falla la red, crea el usuario directamente en Room (modo offline).
     *
     * @param nombre Nombre completo del usuario a registrar.
     * @param email Correo electrónico del usuario.
     * @param password Contraseña asociada al usuario.
     * @param onError Callback que se ejecuta en caso de error inesperado.
     * @param onSuccess Callback que se ejecuta cuando el usuario se crea
     * correctamente. Devuelve un objeto [UserDTO] con los datos del usuario.
     */
    override fun crearUsuario(
        nombre: String,
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Remoto Retrofit
                val request = RegisterDto(
                    name = nombre,
                    email = email,
                    passwd = password
                )

                val response = authApi.register(request)

                if (response.isSuccessful && response.body() != null) {

                    val body = response.body()!!

                    val entity = User(
                        id = body.id,
                        name = body.name,
                        email = body.email,
                        passwd = password,
                        keepLogged = false
                    )

                    // Guardamos en Room
                    userDao.insert(entity)

                    val dto = UserDTO(
                        id = body.id,
                        name = body.name,
                        email = body.email,
                        password = password,
                        keepLogged = false
                    )

                    withContext(Dispatchers.Main) { onSuccess(dto) }
                    return@launch
                }

            } catch (_: Exception) {
                // Si falla red seguimos offline
            }

            // Offline creation ROOM

            val offlineId = UUID.randomUUID().toString()

            val entity = User(
                id = offlineId,
                name = nombre,
                email = email,
                passwd = password,
                keepLogged = false
            )

            userDao.insert(entity)

            val dto = UserDTO(
                id = offlineId,
                name = nombre,
                email = email,
                password = password,
                keepLogged = false
            )

            withContext(Dispatchers.Main) { onSuccess(dto) }
        }
    }
}