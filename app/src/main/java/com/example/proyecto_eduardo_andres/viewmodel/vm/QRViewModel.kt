package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.qrRepository.IQRRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.QRUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @author Andrés
 * @see QRViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de generación/visualización de QR.
 * - Solicita al repositorio los datos necesarios para construir el QR.
 * - Controla estados de carga (loading) y error.
 * - Permite actualizar manualmente el contenido del QR.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación.
 * - Repository → Encargado de obtener los datos del QR.
 *
 * Al inicializarse:
 * - Ejecuta automáticamente la carga del QR.
 *
 * Utiliza:
 * - MutableStateFlow para modificar el estado interno.
 * - StateFlow para exponer estado inmutable a la UI.
 * - update {} para mantener inmutabilidad del estado.
 *
 * En Jetpack Compose:
 * - La UI observa uiState.
 * - Los cambios en qrData, error o isLoading provocan
 *   recomposición automática.
 *
 * @param repository Repositorio encargado de obtener
 * los datos necesarios para generar el QR.
 * @param userId Identificador único del usuario
 * utilizado para generar el contenido del QR.
 *
 * @see QRUiState
 * @see IQRRepository
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 */
class QRViewModel(
    private val repository: IQRRepository,
    private val userId: String
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
    private val userId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QRViewModel::class.java)) {
            return QRViewModel(repository, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}