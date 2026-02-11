package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import android.content.Context
import android.content.SharedPreferences

import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.room.AppDatabase
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepo(
    private val context: Context,
    private val api: UsuarioApiService,
    private val db: AppDatabase
) : IUserRepository {

    private val userDao = db.userDao()

    companion object {
        private const val NAME_KEY = "name_key"
        private const val EMAIL_KEY = "email_key"
        private const val ID_KEY = "id_key"
        private const val PASSWORD_KEY = "password_key"
        private const val KEEP_LOGGED_KEY = "keep_logged_key"
    }

    // --------------------
    // SharedPreferences
    // --------------------
    private fun getSharedPref(): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.preferences_file),
            Context.MODE_PRIVATE
        )
    }

    override fun loginUser(
        user: UserConfig,
        onSuccess: (UserConfig) -> Unit,
        onError: () -> Unit
    ) {
        val sp = getSharedPref()
        try {
            with(sp.edit()) {
                putString(ID_KEY, user.id.toString())
                putString(NAME_KEY, user.name)
                putString(EMAIL_KEY, user.email)
                putString(PASSWORD_KEY, user.password)
                putBoolean(KEEP_LOGGED_KEY, user.keepLogged)
                if (commit()) onSuccess(user) else onError()
            }
        } catch (e: Exception) {
            onError()
        }
    }

    override fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit) {
        val sp = getSharedPref()
        with(sp.edit()) {
            clear()
            if (commit()) onSuccess() else onError()
        }
        // Borrar también de Room
        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteAll()
        }
    }

    override fun getCurrentUser(): UserConfig? {
        val sp = getSharedPref()
        val id = sp.getString(ID_KEY, null)
        val name = sp.getString(NAME_KEY, null)
        val email = sp.getString(EMAIL_KEY, null)
        val password = sp.getString(PASSWORD_KEY, null) ?: ""
        val keepLogged = sp.getBoolean(KEEP_LOGGED_KEY, false)

        return if (id != null && name != null && email != null) {
            UserConfig(id.toInt(), name, email, password, keepLogged)
        } else null
    }

    // --------------------
    // Login Online + Offline
    // --------------------
    override fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.obtenerUsuarios()
                if (response.isSuccessful) {
                    val usersDto = response.body() ?: emptyList()

                    // Guardar en Room
                    val entities = usersDto.map { it.toEntity() }
                    userDao.insertAll(entities)

                    // Buscar usuario
                    val user = userDao.login(email, password)
                    if (user != null) {
                        // Guardar en SharedPreferences
                        loginUser(
                            UserConfig(
                                id = user.id.toIntOrNull() ?: 0,
                                name = user.name,
                                email = user.email,
                                password = user.passwd,
                                keepLogged = true
                            ),
                            onSuccess = { /* ya guardado */ },
                            onError = { /* ignorar */ }
                        )

                        withContext(Dispatchers.Main) {
                            onSuccess(UserDTO(user.id, user.name, user.email, user.passwd))
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError(Throwable("Usuario o contraseña incorrectos"))
                        }
                    }
                } else {
                    // API falla → usar Room offline
                    val user = userDao.login(email, password)
                    if (user != null) {
                        withContext(Dispatchers.Main) {
                            onSuccess(UserDTO(user.id, user.name, user.email, user.passwd))
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError(Throwable("Login fallido"))
                        }
                    }
                }
            } catch (e: Exception) {
                // Offline → usar Room
                val user = userDao.login(email, password)
                if (user != null) {
                    withContext(Dispatchers.Main) {
                        onSuccess(UserDTO(user.id, user.name, user.email, user.passwd))
                    }
                } else {
                    withContext(Dispatchers.Main) { onError(e) }
                }
            }
        }
    }

    override fun getUser(
        id: Int,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = userDao.getById(id.toString())
                if (user != null) {
                    withContext(Dispatchers.Main) {
                        onSuccess(UserDTO(user.id, user.name, user.email, user.passwd))
                    }
                } else {
                    withContext(Dispatchers.Main) { onError(Throwable("Usuario no encontrado")) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    // --------------------
    // Modelos
    // --------------------
    data class UserConfig(
        val id: Int,
        val name: String,
        val email: String,
        val password: String,
        val keepLogged: Boolean
    )
}

