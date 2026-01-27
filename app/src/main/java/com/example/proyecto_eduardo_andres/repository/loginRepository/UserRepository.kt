package com.example.proyecto_eduardo_andres.repository.loginRepository

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.dto.LoginDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Implementacion en memoria de un repositorio de usuarios. Usar solo para ejemplos o pruebas.
 */
class UserRepositoryInMemory(
private val authApi: AuthApiService
) : IUserRepository {

    override fun getUser(
        id: Int,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        onError(Throwable("No implementado"))
    }

    override fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = LoginDto(email = email, passwd = password)
                val response = authApi.login(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val user = UserDTO(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password = password,

                        )
                        withContext(Dispatchers.Main) { onSuccess(user) }
                    } else {
                        withContext(Dispatchers.Main) { onError(Throwable("Respuesta vacía")) }
                    }
                } else {
                    withContext(Dispatchers.Main) { onError(Throwable("Login fallido: ${response.code()}")) }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }
}
