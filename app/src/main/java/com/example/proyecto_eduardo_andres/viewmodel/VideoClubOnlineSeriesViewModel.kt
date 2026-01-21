package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.SeriesData
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoClubOnlineSeriesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlineSeriesUiState())
    val uiState: StateFlow<VideoClubOnlineSeriesUiState> = _uiState.asStateFlow()

    init {
        cargarSeries()
    }

    private fun cargarSeries() {
        val serie = SeriesData().series
        val agrupadas = serie.groupBy { it.categoria }

        _uiState.update {
            it.copy(
                series = serie,
                seriesPorCategoria = agrupadas
            )
        }
    }

    fun filtrarPorCategoria(categoria: Int) {
        val filtradas = _uiState.value.series
            .filter { it.categoria == categoria }

        _uiState.update {
            it.copy(
                seriesPorCategoria = mapOf(categoria to filtradas)
            )
        }
    }
}