package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.dto.LoginDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Implementación en memoria de [IUserRepository].
 *
 * Esta clase simula el almacenamiento de sesión únicamente en RAM.
 * Se utiliza principalmente para:
 * - Testing
 * - Previews
 * - Modo desarrollo sin persistencia real
 *
 * Internamente mantiene un usuario actual en memoria.
 *
 * @property authApi Servicio Retrofit utilizado para realizar login remoto.
 *
 * @author Eduardo
 * @see IUserRepository
 * @see UserRepo.UserConfig
 */
class UserRepositoryInMemory(
private val authApi: AuthApiService
) : IUserRepository {

    /**
     * Usuario actualmente autenticado en memoria.
     */
    private var currentUser: UserRepo.UserConfig? = null

    /**
     * Obtiene el usuario actual almacenado en memoria por su ID.
     *
     * @param id Identificador único del usuario.
     * @param onError Callback ejecutado si no existe usuario en memoria
     * o el ID no coincide.
     * @param onSuccess Callback que devuelve el [UserDTO] correspondiente.
     */
    override fun getUser(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit,
    ) {
        currentUser?.let {
            if (it.id == id) {
                onSuccess(UserDTO(it.id.toString(), it.name, it.email, "", it.keepLogged))
            } else {
                onError(Throwable("Usuario no encontrado en memoria"))
            }
        } ?: onError(Throwable("No hay usuario en memoria"))
    }



    /**
     * Realiza autenticación contra la API mediante Retrofit.
     * Si es exitosa, guarda el usuario en memoria.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param keepLogged Indica si la sesión debe mantenerse activa.
     * @param onError Callback ejecutado si el login falla.
     * @param onSuccess Callback que devuelve el [UserDTO] autenticado.
     */
    override fun login(
        email: String,
        password: String,
        keepLogged: Boolean,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = LoginDto(email = email, passwd = password)
                val response = authApi.login(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        // Creamos UserDTO y UserConfig en memoria
                        val user = UserDTO(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password = password,
                            keepLogged = keepLogged
                        )
                        currentUser = UserRepo.UserConfig(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password= password,
                            keepLogged = keepLogged
                        )
                        withContext(Dispatchers.Main) { onSuccess(user) }
                    } else {
                        withContext(Dispatchers.Main) { onError(Throwable("Respuesta vacía")) }
                    }
                } else {
                    withContext(Dispatchers.Main) { onError(Throwable("Login fallido: ${response.code()}")) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    /**
     * Guarda manualmente un usuario como sesión activa en memoria.
     *
     * @param user Configuración del usuario autenticado.
     * @param onSuccess Callback ejecutado cuando la sesión se guarda correctamente.
     * @param onError Callback ejecutado si ocurre algún error (no usado en memoria).
     */
    override fun loginUser(
        user: UserRepo.UserConfig,
        onSuccess: (UserRepo.UserConfig) -> Unit,
        onError: () -> Unit
    ) {
        currentUser = user
        onSuccess(user)
    }

    /**
     * Elimina la sesión actual almacenada en memoria.
     *
     * @param onSuccess Callback ejecutado cuando el logout se realiza correctamente.
     * @param onError Callback ejecutado si ocurre un error (no usado en memoria).
     */
    override fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit) {
        currentUser = null
        onSuccess()
    }

    /**
     * Devuelve el usuario actualmente almacenado en memoria.
     *
     * @return [UserRepo.UserConfig] o null si no hay sesión activa.
     */
    override fun getCurrentUser(): UserRepo.UserConfig? {
        return currentUser
    }
}