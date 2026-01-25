package com.example.proyecto_eduardo_andres.viewData.perfilUsuarioData

import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData

data class PerfilSeriesUiState(
    val nombreUsuario: String = "",
    val email: String = "",
    val password: String = "",
    val isEditing: Boolean = false,
    val userId: String = "",
    val seriesAlquiladas: List<VideoClubOnlineSeriesData> = emptyList(),
    val showConfirmacionDialog: Boolean = false
) {
    val isModificarButtonEnabled: Boolean
        get() = nombreUsuario.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank()
}

