package com.example.proyecto_eduardo_andres.viewData.recuperarPasswordData

data class RecuperarPasswordUiState(
    val email : String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val passwordVisible: Boolean = false
) {
    val isLoginButtonEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && repeatPassword.isNotBlank()
}
