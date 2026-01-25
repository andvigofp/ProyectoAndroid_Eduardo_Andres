package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.repository.qrRepository.IQRRepository
import com.example.proyecto_eduardo_andres.viewData.qrData.QRUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QRViewModel(
    private val repository: IQRRepository,
    private val userId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(QRUiState())
    val uiState: StateFlow<QRUiState> = _uiState.asStateFlow()

    init {
        cargarQR()
    }

    /**
     * Carga los datos del QR desde el repositorio
     */
    private fun cargarQR() {
        _uiState.update { it.copy(isLoading = true) }
        repository.obtenerQRData(
            userId = userId,
            onError = { error ->
                _uiState.update {
                    it.copy(
                        error = error.message ?: "Error al cargar QR",
                        isLoading = false
                    )
                }
            },
            onSuccess = { qrData ->
                _uiState.update {
                    it.copy(
                        qrData = qrData,
                        isLoading = false,
                        error = null
                    )
                }
            }
        )
    }

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
     * Recarga los datos del QR
     */
    fun recargarQR() {
        cargarQR()
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

class QRViewModelFactory(
    private val repository: IQRRepository,
    private val userId: Int
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QRViewModel::class.java)) {
            return QRViewModel(repository, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
