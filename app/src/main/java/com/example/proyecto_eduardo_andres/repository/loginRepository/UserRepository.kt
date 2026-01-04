package com.example.proyecto_eduardo_andres.repository.loginRepository

import com.example.proyecto_eduardo_andres.modelo.UserDTO


/**
 * Implementacion en memoria de un repositorio de usuarios. Usar solo para ejemplos o pruebas.
 */
class UserRepositoryInMemory : IUserRepository {

    private val users: ArrayList<UserDTO> = ArrayList()

    constructor() {
        for (i in 1..10) {
            users.add(
                UserDTO(
                    id = i,
                    name = "User $i",
                    email = "user$i@example.com",
                    password = "password$i",
                )
            )
        }
    }

    override fun getUser(
        id: Int,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        val user = users.find { it.id == id }
        if (user != null) {
            onSuccess(user)
        } else {
            onError(Throwable("User not found"))
        }
    }

    override fun login(
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        val user = users.find { it.email == email && it.password == password }
        if (user != null) {
            onSuccess(user)
        } else {
            onError(Throwable("User not found"))
        }
    }

}