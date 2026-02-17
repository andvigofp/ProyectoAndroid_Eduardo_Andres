package com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository

/**
 *
 * Define el contrato para actualizar la contraseña de un usuario
 * utilizando su email como identificador.
 *
 *  @author Eduardo
 *  @see Repositorio encargado de gestionar la recuperación de contraseña
 */
interface IRecuperarPasswordRepository {

    /**
     * Permite actualizar la contraseña de un usuario a partir de su email.
     *
     * @param email Email del usuario que desea recuperar la contraseña.
     * @param newPassword Nueva contraseña que se desea establecer.
     * @param onError Callback que se ejecuta si ocurre algún error
     * durante el proceso de recuperación.
     * @param onSuccess Callback que se ejecuta cuando la contraseña
     * se actualiza correctamente.
     */
    fun recuperarPassword(
        email: String,
        newPassword: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )
}