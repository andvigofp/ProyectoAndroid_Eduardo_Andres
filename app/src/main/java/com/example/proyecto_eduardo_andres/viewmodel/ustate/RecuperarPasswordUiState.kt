package com.example.proyecto_eduardo_andres.viewmodel.ustate

/**
 * @author Andrés
 * @see RecuperarPasswordUiState
 *
 * @param UIState para el registro de usuarios.
 *
 * @param email Email del usuario.
 * @param password Contraseña del usuario.
 * @param repeatPassword Repetición de la contraseña del usuario.
 */
data class RecuperarPasswordUiState(
    val email : String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val passwordVisible: Boolean = false
) {
    /**
     * Indica si el botón de inicio de sesión está habilitado.
     */
    val isLoginButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && repeatPassword.isNotBlank()
}