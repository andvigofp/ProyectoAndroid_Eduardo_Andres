package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.camaraRepository.ICamaraRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.CamaraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @author Andrés
 * @see CamaraViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de cámara.
 * - Solicita al repositorio la acción de hacer una foto.
 * - Actualiza el estado reactivo con la imagen obtenida.
 * - Permite trabajar tanto con URI reales como con imágenes fake (Drawable).
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación.
 * - Repository → Se encarga de la lógica de acceso a datos/cámara.
 *
 * Utiliza:
 * - StateFlow para exponer estado observable en Jetpack Compose.
 * - MutableStateFlow para modificar el estado internamente.
 * - update {} para modificar el estado de forma inmutable.
 *
 *
 * @param repository Repositorio encargado de gestionar
 * la lógica de cámara y devolución de imagen.
 *
 * @see CamaraUiState
 * @see ICamaraRepository
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 * @see asStateFlow
 * @see update
 */
class CamaraViewModel(
private val repository: ICamaraRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CamaraUiState(
            imagenUri = null,
            qrLeido = null
        )
    )
    val uiState: StateFlow<CamaraUiState> = _uiState.asStateFlow()

    // Función para simular hacer foto
    fun onHacerFotoClick() {
        repository.hacerFoto(
            onSuccessUri = { uri ->
                _uiState.update { it.copy(imagenUri = uri, imagenDrawable = null) }
            },
            onSuccessDrawable = { drawable ->
                _uiState.update { it.copy(imagenDrawable = drawable, imagenUri = null) }
            },
            onError = { error ->
                Log.e("CamaraVM", "Error al hacer foto", error)
            }
        )
    }

    // -- Cuando la cámara real devuelve URI
    fun setImagenUri(photoUri: Uri) {
        _uiState.update {
            it.copy(
                imagenUri = photoUri,
                imagenDrawable = null
            )
        }
    }

    // -- Cuando usamos imagen fake
    fun setImagenDrawable(drawable: Int) {
        _uiState.update {
            it.copy(
                imagenDrawable = drawable,
                imagenUri = null
            )
        }
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
