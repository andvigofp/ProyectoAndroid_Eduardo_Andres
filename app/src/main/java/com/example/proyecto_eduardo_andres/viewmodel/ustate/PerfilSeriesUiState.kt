package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

data class PerfilSeriesUiState(
    val nombreUsuario: String = "",
    val email: String = "",
    val password: String = "",
    val isEditing: Boolean = false,
    val userId: String = "",
    val seriesAlquiladas: List<VideoClubOnlineSeriesData> = emptyList(),
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