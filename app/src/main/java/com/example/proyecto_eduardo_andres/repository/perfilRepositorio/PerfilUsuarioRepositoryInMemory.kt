package com.example.proyecto_eduardo_andres.repository.perfilRepositorio

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PerfilUsuarioRepositoryInMemory(
    private val apiService: UsuarioApiService
) : IPerfilUsuarioRepository {

    override fun getUsuario(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.obtenerUsuarios() // Llamada a la API
                if (response.isSuccessful) {
                    val usuarioDto = response.body() // UsuarioDto
                    if (usuarioDto != null) {
                        // Convertimos a nuestro UserDTO
                        val usuario = UserDTO(
                            id = usuarioDto.id,
                            name = usuarioDto.name,
                            email = usuarioDto.email,
                            password = usuarioDto.passwd // mapeamos passwd a password
                        )
                        withContext(Dispatchers.Main) {
                            onSuccess(usuario)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError(Throwable("Usuario no encontrado"))
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Error al obtener usuario: ${response.code()}"))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }

    override fun actualizarUsuario(
        usuario: UserDTO,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        onError(Throwable("Actualización de usuario no implementada en la API"))
    }
}
