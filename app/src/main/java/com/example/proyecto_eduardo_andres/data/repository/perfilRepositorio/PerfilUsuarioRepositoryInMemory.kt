package com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PerfilUsuarioRepositoryInMemory(
    private val apiService: UsuarioApiService
) : IPerfilUsuarioRepository {

    override fun getUsuarioPorId(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.obtenerUsuarios()
                if (response.isSuccessful) {
                    val usuarios = response.body() ?: emptyList()
                    val usuarioDto = usuarios.find { it.id == id } // <-- buscar por id

                    if (usuarioDto != null) {
                        val usuario = UserDTO(
                            id = usuarioDto.id,
                            name = usuarioDto.name,
                            email = usuarioDto.email,
                            password = usuarioDto.passwd
                        )
                        withContext(Dispatchers.Main) { onSuccess(usuario) }
                    } else {
                        withContext(Dispatchers.Main) { onError(Throwable("Usuario no encontrado")) }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Error al obtener usuarios: ${response.code()}"))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
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
