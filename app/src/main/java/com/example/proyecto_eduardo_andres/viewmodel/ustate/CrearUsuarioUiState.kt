package com.example.proyecto_eduardo_andres.viewmodel.ustate

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