package com.example.proyecto_eduardo_andres.viewData.logingData

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false
) {
    val isLoginButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank()
}
