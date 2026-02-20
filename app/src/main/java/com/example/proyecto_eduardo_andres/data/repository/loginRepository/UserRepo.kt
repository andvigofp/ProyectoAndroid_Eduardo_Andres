package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.data.room.AppDatabase
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import com.example.proyecto_eduardo_andres.remote.dto.LoginDto
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
    private val authApi: AuthApiService,
    private val usuarioApi: UsuarioApiService,
    private val context: Context
) : IUserRepository {

    private val prefs =
        context.getSharedPreferences("session_prefs", Context.MODE_PRIVATE)

    private var currentUser: UserConfig? = null

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

                        val user = UserDTO(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password = "",
                            keepLogged = keepLogged
                        )

                        currentUser = UserConfig(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password = "",
                            keepLogged = keepLogged
                        )

                        // Guardamos SOLO si quiere mantener sesión
                        if (keepLogged) {
                            prefs.edit()
                                .putString("user_id", body.id)
                                .putBoolean("keep_logged", true)
                                .apply()
                        } else {
                            prefs.edit().clear().apply()
                        }

                        withContext(Dispatchers.Main) {
                            onSuccess(user)
                        }

                    } else {
                        withContext(Dispatchers.Main) {
                            onError(Throwable("Respuesta vacía"))
                        }
                    }

                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Credenciales incorrectas"))
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }

    override fun getCurrentUser(): UserConfig? {

        // Primero intentamos sesión en memoria
        if (currentUser != null) return currentUser

        // Luego intentamos sesión persistida
        val keepLogged = prefs.getBoolean("keep_logged", false)
        if (!keepLogged) return null

        val userId = prefs.getString("user_id", null) ?: return null

        return UserConfig(
            id = userId,
            name = "",
            email = "",
            password = "",
            keepLogged = true
        )
    }

    override fun loggoutUser(
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        prefs.edit().clear().apply()
        currentUser = null
        onSuccess()
    }

    override suspend fun isServerAvailable(): Boolean {
        return try {
            val response = usuarioApi.obtenerUsuarios()
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override fun loginUser(
        user: UserConfig,
        onSuccess: (UserConfig) -> Unit,
        onError: () -> Unit
    ) {
        currentUser = user
        onSuccess(user)
    }

    override fun getUser(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        currentUser?.let {
            if (it.id == id) {
                onSuccess(
                    UserDTO(
                        it.id,
                        it.name,
                        it.email,
                        "",
                        it.keepLogged
                    )
                )
            } else {
                onError(Throwable("Usuario no encontrado"))
            }
        } ?: onError(Throwable("No hay sesión activa"))
    }

    data class UserConfig(
        val id: String,
        val name: String,
        val email: String,
        val password: String,
        val keepLogged: Boolean
    )
}