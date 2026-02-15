package com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository

class RecuperarPasswordRepositoryInMemory :
    IRecuperarPasswordRepository {
    override fun recuperarPassword(
        email: String,
        newPassword: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }
}