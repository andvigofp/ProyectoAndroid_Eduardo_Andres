package com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository

class RecuperarPasswordRepositoryInMemory :
    com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.IRecuperarPasswordRepository {
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