package com.example.proyecto_eduardo_andres.modelo

import com.example.proyecto_eduardo_andres.data.room.entity.User

data class UserDTO(
    val id: String?,
    val name: String,
    val email: String,
    val password: String,
    val keepLogged: Boolean,
) {
    fun toUser(): User {
        return User(
            id = id ?: "",
            name = name,
            email = email,
            passwd = password,
            keepLogged = keepLogged
        )
    }
}
