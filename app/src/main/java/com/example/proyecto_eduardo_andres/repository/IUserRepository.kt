package com.example.proyecto_eduardo_andres.repository

import com.example.proyecto_eduardo_andres.modelo.UserDTO

interface IUserRepository {

    fun getUser(id: Int, onError: (Throwable) -> Unit, onSuccess: (UserDTO) -> Unit)
    fun login(email: String, password: String, onError: (Throwable) -> Unit, onSuccess: (UserDTO) -> Unit)
}
