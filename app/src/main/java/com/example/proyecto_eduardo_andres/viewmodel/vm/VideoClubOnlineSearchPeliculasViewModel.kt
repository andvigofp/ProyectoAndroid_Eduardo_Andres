package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository.AlquilerSearchPeliculasRepository
import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.PeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.viewmodel.ustate.VideoClubOnlineSearchPeliculasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoClubOnlineSearchPeliculasViewModel(
    private val repository: AlquilerSearchPeliculasRepository // Usamos el repositorio correcto
) : ViewModel() {
    private val _uiState = MutableStateFlow(VideoClubOnlineSearchPeliculasUiState())
    val uiState: StateFlow<VideoClubOnlineSearchPeliculasUiState> =
        _uiState.asStateFlow()

    init {
        cargarPeliculas()
    }

    private fun cargarPeliculas() {
        repository.obtenerPeliculasSearch(
            onError = { /* manejar error */ },
            onSuccess = { peliculas ->
                _uiState.update {
                    it.copy(
                        peliculas = peliculas,
                        peliculasFiltradas = peliculas
                    )
                }
            }
        )
    }

    fun onQueryChange(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }
}


class VideoClubOnlineSearchPeliculasViewModelFactory(
    private val repository: AlquilerSearchPeliculasRepository // Usamos el repositorio correcto
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoClubOnlineSearchPeliculasViewModel::class.java)) {
            return VideoClubOnlineSearchPeliculasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
