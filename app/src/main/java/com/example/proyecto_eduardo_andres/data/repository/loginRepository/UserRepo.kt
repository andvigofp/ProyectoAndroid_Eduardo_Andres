package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.data.room.AppDatabase
import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Implementación principal de [IUserRepository].
 *
 * Esta clase actúa como repositorio híbrido de autenticación:
 * - Utiliza Retrofit para login remoto.
 * - Utiliza Room para persistencia local de sesión.
 *
 * Se encarga de:
 * - Autenticación de usuario.
 * - Gestión de sesión.
 * - Persistencia offline.
 *
 * @property context Contexto de la aplicación necesario para
 * inicializar la base de datos Room.
 *
 * @author Andrés
 * @see IUserRepository
 * @see UserRepositoryHibridoLogin
 * @see AppDatabase
 */
class UserRepo(
    context: Context
) : IUserRepository {

    private val userDao = AppDatabase
        .getDatabase(context)
        .userDao()

    private val repositorioHibrido = UserRepositoryHibridoLogin(
        usuarioApi = RetrofitClient.usuarioApiService,
        userDao = userDao
    )

    /**
     * Realiza login híbrido (Retrofit + Room).
     *
     * Flujo:
     * 1. Intenta autenticación remota.
     * 2. Si es válida, elimina sesión previa.
     * 3. Guarda nueva sesión en Room.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña introducida.
     * @param keepLogged Indica si el usuario desea mantener sesión activa.
     * @param onError Callback ejecutado si la autenticación falla.
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

            val userDto = repositorioHibrido.login(email, password, keepLogged)

            if (userDto != null) {

                // Eliminamos sesión anterior
                userDao.deleteAll()

                // Guardamos usuario como sesión activa
                val userEntity = userDto.toUser().copy(
                    keepLogged = keepLogged
                )

                userDao.insert(userEntity)

                withContext(Dispatchers.Main) {
                    onSuccess(userDto)
                }

            } else {
                withContext(Dispatchers.Main) {
                    onError(Throwable("Usuario no encontrado"))
                }
            }
        }
    }

    /**
     * Guarda manualmente una sesión en Room.
     *
     * @param user Configuración del usuario autenticado.
     * @param onSuccess Callback ejecutado cuando la sesión se guarda correctamente.
     * @param onError Callback ejecutado si ocurre un error durante el guardado.
     */
    override fun loginUser(
        user: UserConfig,
        onSuccess: (UserConfig) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userDao.deleteAll()

                userDao.insert(
                    User(
                        id = user.id,
                        name = user.name,
                        email = user.email,
                        passwd = "", // no guardamos password
                        keepLogged = user.keepLogged
                    )
                )

                withContext(Dispatchers.Main) {
                    onSuccess(user)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }

    /**
     * Recupera el usuario actualmente autenticado desde Room.
     *
     * @return [UserConfig] si existe sesión activa, null en caso contrario.
     */
    override fun getCurrentUser(): UserConfig? {

        val user = userDao.getLoggedUser()

        return user?.let {
            UserConfig(
                id = it.id,
                name = it.name,
                email = it.email,
                password = "",
                keepLogged = it.keepLogged
            )
        }
    }

    /**
     * Cierra sesión eliminando todos los registros de usuario en Room.
     *
     * @param onSuccess Callback ejecutado cuando el logout es exitoso.
     * @param onError Callback ejecutado si ocurre un error.
     */
    override fun loggoutUser(
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userDao.deleteAll()

                withContext(Dispatchers.Main) {
                    onSuccess()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError()
                }
            }
        }
    }

    /**
     * Obtiene un usuario específico utilizando el repositorio híbrido.
     *
     * @param id Identificador único del usuario.
     * @param onError Callback ejecutado si el usuario no existe.
     * @param onSuccess Callback que devuelve el [UserDTO] encontrado.
     */
    override fun getUser(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val user = repositorioHibrido.getUser(id)

            withContext(Dispatchers.Main) {
                if (user != null) onSuccess(user)
                else onError(Throwable("Usuario no encontrado"))
            }
        }
    }

    /**
     * Configuración interna utilizada para manejar sesión de usuario.
     *
     * @property id Identificador único del usuario.
     * @property name Nombre del usuario.
     * @property email Correo electrónico.
     * @property password Contraseña (no persistida en Room).
     * @property keepLogged Indica si la sesión debe mantenerse activa.
     */
    data class UserConfig(
        val id: String,
        val name: String,
        val email: String,
        val password: String,
        val keepLogged: Boolean
    )
}