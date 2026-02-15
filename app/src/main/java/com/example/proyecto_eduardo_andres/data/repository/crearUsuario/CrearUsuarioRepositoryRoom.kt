package com.example.proyecto_eduardo_andres.data.repository.crearUsuario

import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.dto.RegisterDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class CrearUsuarioRepositoryRoom(
    private val authApi: AuthApiService,
    private val userDao: UserDao
) : ICrearUsuarioRepository {

    override fun crearUsuario(
        nombre: String,
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Remoto Retrofit
                val request = RegisterDto(
                    name = nombre,
                    email = email,
                    passwd = password
                )

                val response = authApi.register(request)

                if (response.isSuccessful && response.body() != null) {

                    val body = response.body()!!

                    val entity = User(
                        id = body.id,
                        name = body.name,
                        email = body.email,
                        passwd = password,
                        keepLogged = false
                    )

                    // Guardamos en Room
                    userDao.insert(entity)

                    val dto = UserDTO(
                        id = body.id,
                        name = body.name,
                        email = body.email,
                        password = password,
                        keepLogged = false
                    )

                    withContext(Dispatchers.Main) { onSuccess(dto) }
                    return@launch
                }

            } catch (_: Exception) {
                // Si falla red seguimos offline
            }

            // Offline creation ROOM

            val offlineId = UUID.randomUUID().toString()

            val entity = User(
                id = offlineId,
                name = nombre,
                email = email,
                passwd = password,
                keepLogged = false
            )

            userDao.insert(entity)

            val dto = UserDTO(
                id = offlineId,
                name = nombre,
                email = email,
                password = password,
                keepLogged = false
            )

            withContext(Dispatchers.Main) { onSuccess(dto) }
        }
    }
}