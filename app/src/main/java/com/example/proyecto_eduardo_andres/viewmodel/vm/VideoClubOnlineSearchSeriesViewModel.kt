package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository.IAlquilerSearchSeriesRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.VideoClubOnlineSearchSeriesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VideoClubOnlineSearchSeriesViewModel(
    private val repository: IAlquilerSearchSeriesRepository,
    private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlineSearchSeriesUiState())
    val uiState: StateFlow<VideoClubOnlineSearchSeriesUiState> =
        _uiState.asStateFlow()

    init {
        cargarSeries()
    }

    private fun cargarSeries() {
        repository.obtenerSeriesSearch(
            onError = {
                _uiState.update { it.copy(isLoading = false) }
            },
            onSuccess = { series ->
                _uiState.update {
                    it.copy(
                        series = series,
                        seriesFiltradas = series,
                        isLoading = false
                    )
                }
            }
        )
    }

    fun onQueryChange(query: String) {
        _uiState.update { state ->

            val filtradas = state.series.filter { serie ->
                val nombreReal = context.getString(serie.nombre)
                nombreReal.contains(query, ignoreCase = true)
            }

            state.copy(
                query = query,
                seriesFiltradas = filtradas
            )
        }
    }
}

class VideoClubOnlineSearchSeriesViewModelFactory(
    private val repository: IAlquilerSearchSeriesRepository,
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoClubOnlineSearchSeriesViewModel::class.java)) {
            return VideoClubOnlineSearchSeriesViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}