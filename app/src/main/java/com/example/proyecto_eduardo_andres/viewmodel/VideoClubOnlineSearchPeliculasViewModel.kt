package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.PeliculasData
import com.example.proyecto_eduardo_andres.viewData.searchPeliculasData.VideoClubOnlineSearchPeliculasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoClubOnlineSearchPeliculasViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(VideoClubOnlineSearchPeliculasUiState())
    val uiState: StateFlow<VideoClubOnlineSearchPeliculasUiState> =
        _uiState.asStateFlow()

    init {
        cargarPeliculas()
    }

    private fun cargarPeliculas() {
        val peliculas = PeliculasData().peliculas

        _uiState.update {
            it.copy(
               peliculas = peliculas,
                peliculasFiltradas = peliculas
            )
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }

}