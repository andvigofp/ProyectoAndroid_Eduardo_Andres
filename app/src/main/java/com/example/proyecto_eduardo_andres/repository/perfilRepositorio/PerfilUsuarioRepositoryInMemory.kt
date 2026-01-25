package com.example.proyecto_eduardo_andres.repository.perfilRepositorio

import com.example.proyecto_eduardo_andres.modelo.UserDTO

class PerfilUsuarioRepositoryInMemory : IPerfilUsuarioRepository {

    // Lista interna de usuarios (simula la base de datos)
    private val usuarios = mutableListOf(
        UserDTO(
            id = "1",
            name = "Juan Pérez",
            email = "juan@email.com",
            password = "123456"
        ),
        UserDTO(
            id = "2",
            name = "Ana López",
            email = "ana@email.com",
            password = "abcdef"
        )
    )

    // Obtener usuario por ID
    override fun getUsuario(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        val usuario = usuarios.find { it.id == id }
        if (usuario != null) {
            onSuccess(usuario)
        } else {
            onError(Throwable("Usuario no encontrado"))
        }
    }

    // Actualizar usuario
    override fun actualizarUsuario(
        usuario: UserDTO,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        // El ID debe existir
        val id = usuario.id
        if (id == null) {
            onError(Throwable("ID de usuario inválido"))
            return
        }

        val index = usuarios.indexOfFirst { it.id == id }
        if (index != -1) {
            usuarios[index] = usuario
            onSuccess()
        } else {
            onError(Throwable("Usuario no encontrado"))
        }
    }
}
