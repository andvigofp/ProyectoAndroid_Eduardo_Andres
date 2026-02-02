package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.data.repository.seriesRepository.ISeriesRepository
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.viewmodel.ustate.VideoClubOnlineSeriesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoClubOnlineSeriesViewModel(
    private val repository: ISeriesRepository,
    private val sessionEvents: SessionEvents = SessionEvents // Inyectable para testing
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlineSeriesUiState())
    val uiState: StateFlow<VideoClubOnlineSeriesUiState> = _uiState.asStateFlow()

    init {
        cargarSeries()
    }

    private fun cargarSeries() {
        repository.obtenerSeries(
            onError = { /* manejar error */ },
            onSuccess = { series ->
                val agrupadas = series.groupBy { it.categoria }
                _uiState.update {
                    it.copy(
                        series = series,
                        seriesPorCategoria = agrupadas
                    )
                }
            }
        )
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

    // =========================
    // NUEVA FUNCIÓN: cuando el usuario toca una serie
    // =========================
    fun onSerieClick(userId: String, serie: VideoClubOnlineSeriesData) {
        // Emitimos la navegación usando SessionEvents
        viewModelScope.launch {
            sessionEvents.emitNavigation(
                RouteNavigation.AlquilerDevolverSeries(
                    userId = userId,
                    serieId = serie.id,
                )
            )
        }
    }
}

class VideoClubOnlineSeriesViewModelFactory(
    private val repository: ISeriesRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoClubOnlineSeriesViewModel::class.java)) {
            return VideoClubOnlineSeriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
