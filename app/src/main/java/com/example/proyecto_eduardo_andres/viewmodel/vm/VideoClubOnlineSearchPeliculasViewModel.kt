package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository.IAlquilerSearchPeliculasRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.VideoClubOnlineSearchPeliculasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoClubOnlineSearchPeliculasViewModel(
    private val repository: IAlquilerSearchPeliculasRepository,
    private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlineSearchPeliculasUiState())
    val uiState: StateFlow<VideoClubOnlineSearchPeliculasUiState> =
        _uiState.asStateFlow()

    init {
        cargarPeliculas()
    }

    private fun cargarPeliculas() {
        repository.obtenerPeliculasSearch(
            onError = {
                _uiState.update { it.copy(isLoading = false) }
            },
            onSuccess = { peliculas ->
                _uiState.update {
                    it.copy(
                        peliculas = peliculas,
                        peliculasFiltradas = peliculas,
                        isLoading = false
                    )
                }
            }
        )
    }

    fun onQueryChange(query: String) {
        _uiState.update { state ->

            val filtradas = state.peliculas.filter { pelicula ->
                val nombreReal = context.getString(pelicula.nombre)
                nombreReal.contains(query, ignoreCase = true)
            }

            state.copy(
                query = query,
                peliculasFiltradas = filtradas
            )
        }
    }
}



class VideoClubOnlineSearchPeliculasViewModelFactory(
    private val repository: IAlquilerSearchPeliculasRepository,
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoClubOnlineSearchPeliculasViewModel::class.java)) {
            return VideoClubOnlineSearchPeliculasViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

