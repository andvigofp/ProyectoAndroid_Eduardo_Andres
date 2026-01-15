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
        val peliculas = PeliculasData().nombrePeliculas

        _uiState.update {
            it.copy(
               peliculas = peliculas,
                peliculasFiltradas = peliculas
            )
        }
    }

    fun onQueryChange(query: String) {
        val peliculasFiltradas = _uiState.value.peliculas.filter { pelicula ->
            // OJO: aquí NO usamos stringResource en el ViewModel
            // asumimos que nombreSerie es Int (string resource)
            query.isBlank() ||
                    pelicula.nombrePelicula.toString()
                        .contains(query, ignoreCase = true)
        }

        _uiState.update {
            it.copy(
                query = query,
                peliculasFiltradas = peliculasFiltradas
            )
        }
    }
}