package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import com.example.proyecto_eduardo_andres.modelo.UserDTO

interface IUserRepository {

    fun getUser(id: Int, onError: (Throwable) -> Unit, onSuccess: (UserDTO) -> Unit)
    fun login(email: String, password: String, onError: (Throwable) -> Unit, onSuccess: (UserDTO) -> Unit)

    fun loginUser(user: UserRepo.UserConfig, onSuccess: (UserRepo.UserConfig) -> Unit, onError: () -> Unit
    )

    fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit)
    fun getCurrentUser(): UserRepo.UserConfig?
}