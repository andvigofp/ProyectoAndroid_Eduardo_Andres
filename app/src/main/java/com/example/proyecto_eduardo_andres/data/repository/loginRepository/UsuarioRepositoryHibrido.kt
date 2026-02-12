package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import com.example.proyecto_eduardo_andres.remote.dto.LoginDto
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
    private val context: Context,
    private val usuarioApi: UsuarioApiService,
    private val userDao: UserDao
) : IUserRepository {

    private var currentUser: UserRepo.UserConfig? = null

    private fun tieneInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        // Usamos viewModelScope desde Compose o CoroutineScope adecuado
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var userFound: UserDTO? = null

                // Intento remoto
                if (tieneInternet()) {
                    val response = usuarioApi.obtenerUsuarios()
                    if (response.isSuccessful && response.body() != null) {
                        val users = response.body() as? List<UsuarioDto>
                        val remoteUser = users?.firstOrNull { it.email == email && it.passwd == password }
                        remoteUser?.let {
                            val user = it.toUser().copy(keepLogged = true)
                            userDao.insert(user) // Guardamos en Room
                            currentUser = UserRepo.UserConfig(
                                id = user.id.toIntOrNull() ?: 0,
                                name = user.name,
                                email = user.email,
                                password = user.passwd,
                                keepLogged = true
                            )
                            userFound = UserDTO(user.id, user.name, user.email, user.passwd)
                        }
                    }
                }

                // Intento offline si falla remoto
                if (userFound == null) {
                    val localUser = userDao.login(email, password)
                    localUser?.let {
                        currentUser = UserRepo.UserConfig(
                            id = it.id.toIntOrNull() ?: 0,
                            name = it.name,
                            email = it.email,
                            password = it.passwd,
                            keepLogged = true
                        )
                        userFound = UserDTO(it.id, it.name, it.email, it.passwd)
                    }
                }

                withContext(Dispatchers.Main) {
                    if (userFound != null) onSuccess(userFound!!)
                    else onError(Throwable("Usuario no encontrado o credenciales incorrectas"))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }


    override fun getUser(id: Int, onError: (Throwable) -> Unit, onSuccess: (UserDTO) -> Unit) {
        currentUser?.let {
            if (it.id == id) {
                onSuccess(UserDTO(it.id.toString(), it.name, it.email, it.password))
            } else {
                onError(Throwable("Usuario no encontrado"))
            }
        } ?: onError(Throwable("No hay usuario en memoria"))
    }

    override fun loginUser(user: UserRepo.UserConfig, onSuccess: (UserRepo.UserConfig) -> Unit, onError: () -> Unit) {
        currentUser = user
        onSuccess(user)
    }

    override fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit) {
        currentUser = null
        onSuccess()
    }

    override fun getCurrentUser(): UserRepo.UserConfig? = currentUser
}
