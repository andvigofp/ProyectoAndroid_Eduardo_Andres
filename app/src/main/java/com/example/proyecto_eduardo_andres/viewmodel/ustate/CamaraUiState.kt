package com.example.proyecto_eduardo_andres.viewmodel.ustate

import android.net.Uri

/**
 * @author Andrés
 * @see CamaraUiState
 *
 * @param UIState para la cámara.
 *
 * @param imagenUri Uri de la imagen capturada.
 * @param imagenDrawable Drawable de la imagen capturada.
 * @param qrLeido Código QR leído.
 */
data class CamaraUiState(
    val imagenUri: Uri? = null,
    val imagenDrawable: Int? = null,
    val qrLeido: String? = null,
)