package com.example.proyecto_eduardo_andres.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.repository.camaraRepository.ICamaraRepository
import com.example.proyecto_eduardo_andres.viewData.camaraData.CamaraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CamaraViewModel(
private val repository: ICamaraRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CamaraUiState(
            imagenCamara = null,
            qrLeido = null
        )
    )
    val uiState: StateFlow<CamaraUiState> = _uiState.asStateFlow()

    // Función para simular hacer foto
    fun onHacerFotoClick() {
        repository.hacerFoto(
            onSuccess = { drawableRes ->
                _uiState.update { it.copy(imagenCamara = drawableRes) }
            },
            onError = { error ->
                Log.e("CamaraViewModel", "Error al hacer foto", error)
            }
        )
    }

    // Función para simular leer QR
    fun onLeerQrClick() {
        repository.leerQr(
            onSuccess = { qr ->
                _uiState.update { it.copy(qrLeido = qr) }
            },
            onError = { error ->
                Log.e("CamaraViewModel", "Error al leer QR", error)
            }
        )
    }
}
class CamaraViewModelFactory(
    private val repository: ICamaraRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CamaraViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CamaraViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
