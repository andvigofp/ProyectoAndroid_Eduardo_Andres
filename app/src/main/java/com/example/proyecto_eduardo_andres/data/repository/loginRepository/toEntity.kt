package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto

fun UsuarioDto.toEntity(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        passwd = this.passwd,
        keepLogged = false
    )
}
