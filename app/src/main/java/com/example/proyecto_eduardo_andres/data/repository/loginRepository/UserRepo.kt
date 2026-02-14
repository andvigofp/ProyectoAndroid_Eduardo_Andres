package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import android.content.Context
import android.content.SharedPreferences

import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.room.AppDatabase
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepo(
    private val context: Context
) : IUserRepository {

    private val db = AppDatabase.getDatabase(context)
    private val userDao = db.userDao()

    private val repositorioHibrido = UserRepositoryHibridoLogin(
        usuarioApi = RetrofitClient.usuarioApiService,
        userDao = userDao
    )

    companion object {
        private const val NAME_KEY = "name_key"
        private const val EMAIL_KEY = "email_key"
        private const val ID_KEY = "id_key"
        private const val PASSWORD_KEY = "password_key"
        private const val KEEP_LOGGED_KEY = "keep_logged_key"
    }

    private fun getSharedPref(): SharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.preferences_file),
            Context.MODE_PRIVATE
        )

    // ================= LOGIN =================

    override fun login(
        email: String,
        password: String,
        keepLogged: Boolean,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {

        CoroutineScope(Dispatchers.IO).launch {

            val userDto = repositorioHibrido.login(email, password, keepLogged)

            withContext(Dispatchers.Main) {

                if (userDto != null) {

                    val userConfig = UserConfig(
                        id = userDto.id ?: "",
                        name = userDto.name,
                        email = userDto.email,
                        password = userDto.password,
                        keepLogged = keepLogged
                    )

                    loginUser(
                        userConfig,
                        onSuccess = { onSuccess(userDto) },
                        onError = { onError(Throwable("Error guardando sesión")) }
                    )

                } else {
                    onError(Throwable("Usuario no encontrado"))
                }
            }
        }
    }

    // ================= GUARDAR SESIÓN =================

    override fun loginUser(
        user: UserConfig,
        onSuccess: (UserConfig) -> Unit,
        onError: () -> Unit
    ) {

        val sp = getSharedPref()

        with(sp.edit()) {
            putString(ID_KEY, user.id)
            putString(NAME_KEY, user.name)
            putString(EMAIL_KEY, user.email)
            putString(PASSWORD_KEY, user.password)
            putBoolean(KEEP_LOGGED_KEY, user.keepLogged)
            if (commit()) onSuccess(user) else onError()
        }
    }

    // ================= OBTENER SESIÓN =================

    override fun getCurrentUser(): UserConfig? {

        val sp = getSharedPref()

        val id = sp.getString(ID_KEY, null)
        val name = sp.getString(NAME_KEY, null)
        val email = sp.getString(EMAIL_KEY, null)
        val password = sp.getString(PASSWORD_KEY, null) ?: ""
        val keepLogged = sp.getBoolean(KEEP_LOGGED_KEY, false)

        return if (id != null && name != null && email != null && keepLogged) {
            UserConfig(id, name, email, password, keepLogged)
        } else null
    }

    override fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit) {

        val sp = getSharedPref()

        with(sp.edit()) {
            clear()
            if (commit()) onSuccess() else onError()
        }

        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteAll()
        }
    }

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

    data class UserConfig(
        val id: String,
        val name: String,
        val email: String,
        val password: String,
        val keepLogged: Boolean
    )
}
