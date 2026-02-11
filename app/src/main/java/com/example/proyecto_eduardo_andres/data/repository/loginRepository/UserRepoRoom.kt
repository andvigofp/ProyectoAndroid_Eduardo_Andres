package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.data.room.AppDatabase
import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class UserRepoRoom(val context: Context) : IUserRepository {

    private val db by lazy { AppDatabase.getDatabase(context) }
    private val userDao = db.userDao()

    // Login con email + password (offline)
    override fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val user = userDao.login(email, password)
                if (user != null) {
                    withContext(Dispatchers.Main) {
                        onSuccess(
                            UserDTO(
                                id = user.id,
                                name = user.name,
                                email = user.email,
                                password = user.passwd
                            )
                        )
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Email o contraseña incorrectos"))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    // Guardar sesión marcada con keepLogged
    override fun loginUser(
        user: UserRepo.UserConfig,
        onSuccess: (UserRepo.UserConfig) -> Unit,
        onError: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userDao.insert(
                    User(
                        id = user.id.toString(),
                        name = user.name,
                        email = user.email,
                        passwd = user.password,
                        keepLogged = user.keepLogged
                    )
                )
                withContext(Dispatchers.Main) { onSuccess(user) }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    // Cerrar sesión
    override fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userDao.deleteAll()
                withContext(Dispatchers.Main) { onSuccess() }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError() }
            }
        }
    }

    // Obtener usuario actualmente logeado
    override fun getCurrentUser(): UserRepo.UserConfig? {
        // Como getAll es suspend, debemos usar runBlocking si lo llamamos fuera de corutina
        return runBlocking {
            val users = userDao.getAll()
            if (users.isNotEmpty()) {
                val u = users[0] // asumimos un solo usuario marcado keepLogged
                UserRepo.UserConfig(
                    id = u.id.toInt(),
                    name = u.name,
                    email = u.email,
                    password = u.passwd,
                    keepLogged = u.keepLogged
                )
            } else null
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
}
