package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Repositorio Híbrido que carga desde Retrofit (API remota) si hay internet,
 * y desde Room (BD local) si no hay internet. Sincroniza automáticamente los datos.
 */
class UserRepositoryHibridoLogin(
    private val usuarioApi: UsuarioApiService,
    private val userDao: UserDao
) : IUserRepository {

    private var currentUser: UserRepo.UserConfig? = null


    //LOGIN HÍBRIDO REAL
    override fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            var userFound: UserDTO? = null

            try {
                // =====================
                // 1️. Intento REMOTO
                // =====================
                val response = usuarioApi.obtenerUsuarios()

                if (response.isSuccessful && response.body() != null) {

                    val remoteUser = response.body()!!
                        .firstOrNull { it.email == email && it.passwd == password }

                    remoteUser?.let { dto ->

                        val roomUser = dto.toUser().copy(keepLogged = true)
                        userDao.insert(roomUser)

                        currentUser = UserRepo.UserConfig(
                            id = dto.id,
                            name = dto.name,
                            email = dto.email,
                            password = dto.passwd,
                            keepLogged = true
                        )

                        userFound = UserDTO(dto.id, dto.name, dto.email, dto.passwd)
                    }
                }

            } catch (e: Exception) {
                // Si falla Retrofit, NO pasa nada
                // Continuamos con Room
            }

            // =====================
            // 2️. Intento OFFLINE
            // =====================
            if (userFound == null) {

                val localUser = userDao.login(email, password)

                localUser?.let {

                    currentUser = UserRepo.UserConfig(
                        id = it.id,
                        name = it.name,
                        email = it.email,
                        password = it.passwd,
                        keepLogged = true
                    )

                    userFound = UserDTO(it.id, it.name, it.email, it.passwd)
                }
            }

            // =====================
            // 3️. Resultado final
            // =====================
            withContext(Dispatchers.Main) {
                if (userFound != null)
                    onSuccess(userFound!!)
                else
                    onError(Throwable("Usuario no encontrado"))
            }
        }
    }


    // Igual que el del profesor pero con String
    override fun getUser(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        currentUser?.let {
            if (it.id == id) {
                onSuccess(UserDTO(it.id, it.name, it.email, it.password))
            } else onError(Throwable("Usuario no encontrado"))
        } ?: onError(Throwable("No hay usuario en memoria"))
    }

    override fun loginUser(
        user: UserRepo.UserConfig,
        onSuccess: (UserRepo.UserConfig) -> Unit,
        onError: () -> Unit
    ) {
        currentUser = user
        onSuccess(user)
    }

    override fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit) {
        currentUser = null
        CoroutineScope(Dispatchers.IO).launch { userDao.deleteAll() }
        onSuccess()
    }

    override fun getCurrentUser(): UserRepo.UserConfig? = currentUser
}
