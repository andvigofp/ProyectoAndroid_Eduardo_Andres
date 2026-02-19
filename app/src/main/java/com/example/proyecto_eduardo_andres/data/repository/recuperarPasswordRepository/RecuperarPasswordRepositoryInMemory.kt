package com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository

/**
 *
 * Esta implementación simula la actualización de contraseña
 * sin persistencia real ni conexión a red.
 *
 * @author Eduardo
 * @see Implementación en memoria para recuperación de contraseña
 */
class RecuperarPasswordRepositoryInMemory :
    IRecuperarPasswordRepository {

    /**
     * Simula la actualización de la contraseña de un usuario.
     *
     * @param email Email del usuario que desea recuperar la contraseña.
     * @param newPassword Nueva contraseña que se desea establecer.
     * @param onError Callback que se ejecuta si ocurre un error.
     * @param onSuccess Callback que se ejecuta cuando la operación se completa correctamente.
     */
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