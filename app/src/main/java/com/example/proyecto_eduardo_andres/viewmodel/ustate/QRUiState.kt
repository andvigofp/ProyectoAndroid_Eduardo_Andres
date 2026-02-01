package com.example.proyecto_eduardo_andres.viewmodel.ustate

data class QRUiState(
    val qrData: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)