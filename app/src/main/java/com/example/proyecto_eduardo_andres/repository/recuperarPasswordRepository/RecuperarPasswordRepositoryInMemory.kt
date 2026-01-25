package com.example.proyecto_eduardo_andres.repository.recuperarPasswordRepository

class RecuperarPasswordRepositoryInMemory : IRecuperarPasswordRepository {
    override fun recuperarPassword(
        email: String,
        newPassword: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            // Simulación de recuperación de contraseña
            // En una app real, aquí se actualizaría la contraseña en la base de datos
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }
}

