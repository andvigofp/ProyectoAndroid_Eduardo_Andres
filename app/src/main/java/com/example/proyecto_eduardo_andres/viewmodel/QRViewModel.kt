package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyecto_eduardo_andres.viewData.qrData.QRUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QRViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QRUiState())
    val uiState: StateFlow<QRUiState> = _uiState.asStateFlow()

    /**
     * Actualiza el contenido del QR
     */
    fun setQRData(data: String) {
        _uiState.update {
            it.copy(
                qrData = data,
                error = null
            )
        }
    }

    /**
     * Simula cargar datos del QR (por ejemplo desde repo o backend)
     */
    fun loadQR(data: String) {
        _uiState.update { it.copy(isLoading = true) }

        // Si luego usas corrutinas o repositorios
        _uiState.update {
            it.copy(
                qrData = data,
                isLoading = false
            )
        }
    }

    /**
     * Manejo de error (opcional)
     */
    fun setError(message: String) {
        _uiState.update {
            it.copy(
                error = message,
                isLoading = false
            )
        }
    }
}
