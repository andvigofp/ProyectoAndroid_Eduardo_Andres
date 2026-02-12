package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.room.AppDatabase
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepo(
    private val context: Context,
    private val db: AppDatabase
) : IUserRepository {

    private val userDao = db.userDao()
    private val repositorioHibrido = UserRepositoryHibridoLogin(
        context = context,
        userDao = userDao,
        usuarioApi = RetrofitClient.usuarioApiService // <-- Pasamos la API real de Retrofit
    )

    companion object {
        private const val NAME_KEY = "name_key"
        private const val EMAIL_KEY = "email_key"
        private const val ID_KEY = "id_key"
        private const val PASSWORD_KEY = "password_key"
        private const val KEEP_LOGGED_KEY = "keep_logged_key"
        private const val TAG = "UserRepo"
    }

    // --------------------
    // SharedPreferences
    // --------------------
    private fun getSharedPref(): SharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.preferences_file),
            Context.MODE_PRIVATE
        )

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
        } catch (_: Exception) {
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
    // Login Online + Offline (Repositorio Híbrido)
    // --------------------
    override fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        repositorioHibrido.login(email, password, onError, onSuccess)
    }

    override fun getUser(
        id: Int,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        repositorioHibrido.getUser(id, onError, onSuccess)
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

    // Helper de depuración
    @Suppress("unused")
    fun logUsuariosLocal() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val list = userDao.getAll()
                Log.d(TAG, "Usuarios en Room: ${list.size}")
                list.forEach { Log.d(TAG, "User in DB: ${it.id} ${it.email}") }
            } catch (_: Exception) {
                Log.w(TAG, "Error leyendo usuarios locales")
            }
        }
    }
}
