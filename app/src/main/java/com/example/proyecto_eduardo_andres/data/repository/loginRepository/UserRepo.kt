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

    // ================= GUARDAR SESIÓN =================

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

    // ================= OBTENER SESIÓN =================

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

    // ================= LOGOUT =================

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

    // ================= GET USER =================

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
