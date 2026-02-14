package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
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
) {

    suspend fun login(
        email: String,
        password: String,
        keepLogged: Boolean
    ): UserDTO? {

        var userFound: UserDTO? = null

        try {
            val response = usuarioApi.obtenerUsuarios()

            if (response.isSuccessful && response.body() != null) {

                val remoteUser = response.body()!!
                    .firstOrNull { it.email == email && it.passwd == password }

                remoteUser?.let { dto ->

                    val roomUser = dto.toUser().copy(keepLogged = keepLogged)
                    userDao.insert(roomUser)

                    userFound = UserDTO(
                        dto.id,
                        dto.name,
                        dto.email,
                        dto.passwd,
                        keepLogged
                    )
                }
            }

        } catch (_: Exception) {
            // Si falla red → seguimos offline
        }

        if (userFound == null) {

            val localUser = userDao.login(email, password)

            localUser?.let {

                val updatedUser = it.copy(keepLogged = keepLogged)
                userDao.insert(updatedUser)

                userFound = UserDTO(
                    updatedUser.id,
                    updatedUser.name,
                    updatedUser.email,
                    updatedUser.passwd,
                    keepLogged
                )
            }
        }

        return userFound
    }

    suspend fun getUser(id: String): UserDTO? {
        val user = userDao.getById(id)
        return user?.let {
            UserDTO(it.id, it.name, it.email, it.passwd, it.keepLogged)
        }
    }
}