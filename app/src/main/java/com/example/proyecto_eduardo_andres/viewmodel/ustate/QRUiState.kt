package com.example.proyecto_eduardo_andres.viewmodel.ustate

/**
 * @author Andrés
 * @see QRUiState
 *
 * @param UIState para la gestión de QR.
 *
 * @param qrData Datos del QR.
 * @param isLoading Indica si la operación de generación de QR está en proceso.
 * @param error Mensaje de error en caso de que ocurra.
 */
data class QRUiState(
    val qrData: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)