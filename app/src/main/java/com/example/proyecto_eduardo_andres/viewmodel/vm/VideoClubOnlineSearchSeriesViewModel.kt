package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository.IAlquilerSearchSeriesRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.VideoClubOnlineSearchSeriesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoClubOnlineSearchSeriesViewModel(
    private val repository: IAlquilerSearchSeriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlineSearchSeriesUiState())
    val uiState: StateFlow<VideoClubOnlineSearchSeriesUiState> =
        _uiState.asStateFlow()

    init {
        cargarSeries()
    }

    private fun cargarSeries() {
        repository.obtenerSeriesSearch(
            onError = { /* manejar error */ },
            onSuccess = { series ->
                _uiState.update {
                    it.copy(
                        series = series,
                        seriesFiltradas = series
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

class VideoClubOnlineSearchSeriesViewModelFactory(
    private val repository: IAlquilerSearchSeriesRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoClubOnlineSearchSeriesViewModel::class.java)) {
            return VideoClubOnlineSearchSeriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}