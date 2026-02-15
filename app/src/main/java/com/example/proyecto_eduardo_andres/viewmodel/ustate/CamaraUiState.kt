package com.example.proyecto_eduardo_andres.viewmodel.ustate

import android.net.Uri

data class CamaraUiState(
    val imagenUri: Uri? = null,
    val imagenDrawable: Int? = null,
    val qrLeido: String? = null,
)