package com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository

import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.remote.api.RecuperarPasswordApiService
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecuperarPasswordRepositoryRoom(
    private val api: RecuperarPasswordApiService,
    private val userDao: UserDao
) : IRecuperarPasswordRepository {

    override fun recuperarPassword(
        email: String,
        newPassword: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Remoto Retrofit
                val response = api.obtenerUsuarios()

                if (response.isSuccessful && response.body() != null) {

                    val users = response.body()!!
                    val found = users.firstOrNull {
                        it.email.equals(email, ignoreCase = true)
                    }

                    if (found != null) {

                        val updated = UsuarioDto(
                            id = found.id,
                            name = found.name,
                            email = found.email,
                            passwd = newPassword,
                            keepLogged = false
                        )

                        val updateResponse =
                            api.actualizarUsuario(found.id, updated)

                        if (updateResponse.isSuccessful) {

                            // Actualizamos Room
                            userDao.insert(
                                User(
                                    id = found.id,
                                    name = found.name,
                                    email = found.email,
                                    passwd = newPassword,
                                    keepLogged = false
                                )
                            )

                            withContext(Dispatchers.Main) { onSuccess() }
                            return@launch
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError(Throwable("No existe ningún usuario con ese email"))
                        }
                        return@launch
                    }
                }

            } catch (_: Exception) {
                // Si falla red seguimos offline
            }

            // Room local
            val localUser = userDao.getAll()
                .firstOrNull { it.email.equals(email, ignoreCase = true) }

            if (localUser != null) {

                userDao.insert(
                    localUser.copy(passwd = newPassword)
                )

                withContext(Dispatchers.Main) { onSuccess() }

            } else {
                withContext(Dispatchers.Main) {
                    onError(Throwable("No existe ningún usuario con ese email"))
                }
            }
        }
    }
}