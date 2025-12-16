package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.PeliculasData
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoClubOnlinePeliculasViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlinePeliculasUiState())
    val uiState: StateFlow<VideoClubOnlinePeliculasUiState> = _uiState.asStateFlow()

    init {
        cargarPeliculas()
    }

    private fun cargarPeliculas() {
        val peliculas = PeliculasData().nombrePeliculas
        val agrupadas = peliculas.groupBy { it.nombreCategoria }

        _uiState.update {
            it.copy(
                peliculas = peliculas,
                peliculasPorCategoria = agrupadas
            )
        }
    }

    fun filtrarPorCategoria(categoria: Int) {
        val filtradas = _uiState.value.peliculas
            .filter { it.nombreCategoria == categoria }

        _uiState.update {
            it.copy(
                peliculasPorCategoria = mapOf(categoria to filtradas)
            )
        }
    }

}
