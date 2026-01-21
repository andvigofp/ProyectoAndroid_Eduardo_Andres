package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.SeriesData
import com.example.proyecto_eduardo_andres.viewData.searchSeriesData.VideoClubOnlineSearchSeriesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoClubOnlineSearchSeriesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlineSearchSeriesUiState())
    val uiState: StateFlow<VideoClubOnlineSearchSeriesUiState> =
        _uiState.asStateFlow()

    init {
        cargarSeries()
    }

    private fun cargarSeries() {
        val series = SeriesData().series

        _uiState.update {
            it.copy(
                series = series,
                seriesFiltradas = series
            )
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }
}
