package com.example.proyecto_eduardo_andres.repository.recuperarPasswordRepository

interface IRecuperarPasswordRepository {
    fun recuperarPassword(
        email: String,
        newPassword: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )
}

