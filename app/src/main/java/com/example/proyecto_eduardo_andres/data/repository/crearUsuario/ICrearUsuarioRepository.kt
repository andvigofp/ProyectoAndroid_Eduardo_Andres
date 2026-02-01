package com.example.proyecto_eduardo_andres.data.repository.crearUsuario

import com.example.proyecto_eduardo_andres.modelo.UserDTO

interface ICrearUsuarioRepository {
    fun crearUsuario(
        nombre: String,
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    )
}
