package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

data class PerfilUsuarioUiState(
    val nombreUsuario: String = "",
    val email: String = "",
    val password: String = "",
    val isEditing: Boolean = false,
    val userId: String = "",
    val peliculasAlquiladas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val showConfirmacionDialog: Boolean = false,
    val showInfoDialog: Boolean = false,
    val infoDialogTitle: String = "",
    val infoDialogMessage: String = ""
) {
    val isModificarButtonEnabled: Boolean
        get() = nombreUsuario.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank()
}