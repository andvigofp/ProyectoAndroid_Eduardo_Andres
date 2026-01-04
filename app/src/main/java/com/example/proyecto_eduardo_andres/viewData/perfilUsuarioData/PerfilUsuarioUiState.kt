package com.example.proyecto_eduardo_andres.viewData.perfilUsuarioData

data class PerfilUsuarioUiState(
    val nombreUsuario: String = "",
    val email: String = "",
    val password: String = "",
    val isEditing: Boolean = false
) {
    val isModificarButtonEnabled: Boolean
        get() = nombreUsuario.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank()
}
