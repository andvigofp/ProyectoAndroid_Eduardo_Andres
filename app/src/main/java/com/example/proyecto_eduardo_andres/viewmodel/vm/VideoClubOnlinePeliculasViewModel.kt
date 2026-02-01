package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.IPeliculasRepository
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.viewmodel.ustate.VideoClubOnlinePeliculasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoClubOnlinePeliculasViewModel(
    private val repository: com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.IPeliculasRepository,
    private val sessionEvents: SessionEvents = SessionEvents
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlinePeliculasUiState())
    val uiState: StateFlow<VideoClubOnlinePeliculasUiState> = _uiState.asStateFlow()

    init {
        cargarPeliculas()
    }

    private fun cargarPeliculas() {
        repository.obtenerPeliculas(
            onError = { /* manejar error */ },
            onSuccess = { peliculas ->
                val agrupadas = peliculas.groupBy { it.categoria }
                _uiState.update {
                    it.copy(
                        peliculas = peliculas,
                        peliculasPorCategoria = agrupadas
                    )
                }
            }
        )
    }

    fun filtrarPorCategoria(categoria: Int) {
        val filtradas = _uiState.value.peliculas
            .filter { it.categoria == categoria }

        _uiState.update {
            it.copy(
                peliculasPorCategoria = mapOf(categoria to filtradas)
            )
        }
    }

    // =========================
    // NUEVA FUNCIÓN: cuando el usuario toca una serie
    // =========================
    fun onPeliculaClick(userId: Int, pelicula: VideoClubOnlinePeliculasData) {
        // Emitimos la navegación usando SessionEvents
        viewModelScope.launch {
            sessionEvents.emitNavigation(
                RouteNavigation.AlquilerDevolverPeliculas(
                    userId = userId,
                    nombrePelicula = pelicula.nombre,
                )
            )
        }
    }
}

class VideoClubOnlinePeliculasViewModelFactory(
    private val repository: com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.IPeliculasRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoClubOnlinePeliculasViewModel::class.java)) {
            return VideoClubOnlinePeliculasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
