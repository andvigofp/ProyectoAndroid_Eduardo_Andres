package com.example.proyecto_eduardo_andres.data.repository.crearUsuario

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.dto.RegisterDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CrearUsuarioRepositoryInMemory(private val authApi: AuthApiService) :
   ICrearUsuarioRepository {

    override fun crearUsuario(
        nombre: String,
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = RegisterDto(name = nombre, email = email, passwd = password)
                val response = authApi.register(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val user = UserDTO(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password = password,
                            keepLogged = false
                        )
                        withContext(Dispatchers.Main) {
                            onSuccess(user)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError(Throwable("Respuesta vacía"))
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Registro fallido: ${response.code()}"))
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }
}