package com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio

import com.example.proyecto_eduardo_andres.modelo.UserDTO

interface IPerfilUsuarioRepository {
    fun getUsuarioPorId(
        id: String,  // UUID de tipo String
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    )

    fun actualizarUsuario(
        usuario: UserDTO,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )
}
