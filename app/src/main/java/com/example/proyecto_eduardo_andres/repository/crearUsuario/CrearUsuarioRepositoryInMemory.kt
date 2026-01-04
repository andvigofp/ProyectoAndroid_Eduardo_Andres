package com.example.proyecto_eduardo_andres.repository.crearUsuario

import com.example.proyecto_eduardo_andres.modelo.UserDTO

class CrearUsuarioRepositoryInMemory : ICrearUsuarioRepository {

    private val users = mutableListOf<UserDTO>()
    private var nextId = 1

    override fun crearUsuario(
        nombre: String,
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        // Verificar si el email ya existe
        val exists = users.any { it.email == email }
        if (exists) {
            onError(Throwable("El email ya está registrado"))
            return
        }

        val newUser = UserDTO(
            id = nextId++,
            name = nombre,
            email = email,
            password = password,
        )

        users.add(newUser)
        onSuccess(newUser)
    }
}
