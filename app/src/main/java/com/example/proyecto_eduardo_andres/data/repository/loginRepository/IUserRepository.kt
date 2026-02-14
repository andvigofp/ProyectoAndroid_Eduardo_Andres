package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto

interface IUserRepository {

    fun getUser(id: String, onError: (Throwable) -> Unit, onSuccess: (UserDTO) -> Unit)
    fun login(email: String, password: String, keepLogged: Boolean,  onError: (Throwable) -> Unit, onSuccess: (UserDTO) -> Unit)

    fun loginUser(user: UserRepo.UserConfig, onSuccess: (UserRepo.UserConfig) -> Unit, onError: () -> Unit)

    fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit)
    fun getCurrentUser(): UserRepo.UserConfig?
}

fun UsuarioDto.toEntity(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        passwd = this.passwd,
        keepLogged = false
    )
}