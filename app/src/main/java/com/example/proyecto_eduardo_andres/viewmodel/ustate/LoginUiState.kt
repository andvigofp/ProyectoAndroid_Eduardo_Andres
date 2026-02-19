package com.example.proyecto_eduardo_andres.viewmodel.ustate

/**
 * @author Andrés
 * @see LoginUiState
 *
 * @param UIState para el inicio de sesión.
 * @param email Email del usuario.
 * @param password Contraseña del usuario.
 * @param passwordVisible Indica si la contraseña está visible.
 * @param keepLogged Indica si el usuario desea mantener la sesión iniciada.
 * @param isLoading Indica si la operación de inicio de sesión está en proceso.
 */
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val keepLogged: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false
) {
    val isLoginButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank()
}