package com.example.proyecto_eduardo_andres.viewmodel.ustate

/**
 * @author Andrés
 * @see CrearUsuarioUiState
 *
 * @param UIState para el registro de usuarios.
 *
 * @param nombre Nombre del usuario.
 * @param password Contraseña del usuario.
 * @param repeatPassword Repetición de la contraseña del usuario.
 * @param email Email del usuario.
 * @param repeatEmail Repetición del email del usuario.
 * @param passwordVisible Indica si la contraseña está visible.
 * @param repeatPasswordVisible Indica si la contraseña de repetición está visible.
 * @param isLoginButtonEnabled Indica si el botón de inicio de sesión está habilitado.
 * @param isRegisterButtonEnabled Indica si el botón de registro está habilitado.
 */
data class CrearUsuarioUiState(
    val nombre: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val email: String = "",
    val repeatEmail: String = "",
    val passwordVisible: Boolean = false,
    val repeatPasswordVisible: Boolean = false
) {
    val isLoginButtonEnabled: Boolean
        get() = nombre.isNotBlank() &&
                password.isNotBlank() &&
                repeatPassword.isNotBlank() &&
                email.isNotBlank() &&
                repeatEmail.isNotBlank()
}