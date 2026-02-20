package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 * @author Eduardo
 * @see PerfilSeriesUiState
 * @param UIState para el perfil de series.
 *
 * @param nombreUsuario Nombre del usuario.
 * @param email Email del usuario.
 * @param password Contraseña del usuario.
 * @param isEditing Indica si el usuario está editando su perfil.
 * @param userId Identificador único del usuario.
 * @param seriesAlquiladas Lista de series alquiladas por el usuario.
 * @param showConfirmacionDialog Indica si se muestra el diálogo de confirmación.
 * @param showInfoDialog Indica si se muestra el diálogo de información.
 * @param infoDialogTitle Título del diálogo de información.
 * @param infoDialogMessage Mensaje del diálogo de información.
 *
 */
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
    val infoDialogMessage: String = "",
    val isOnline: Boolean = true
) {
    /**
     * Indica si el botón de modificar está habilitado.
     */
    val isModificarButtonEnabled: Boolean
        get() = nombreUsuario.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank()
}