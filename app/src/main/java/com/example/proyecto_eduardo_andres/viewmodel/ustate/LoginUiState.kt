package com.example.proyecto_eduardo_andres.viewmodel.ustate

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