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

/**
 * @author Eduardo
 * @see VideoClubOnlineSeriesViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla principal de series.
 * - Carga el catálogo completo desde el repositorio.
 * - Agrupa las series por categoría.
 * - Permite filtrar series por categoría.
 * - Gestiona eventos de navegación al seleccionar una serie.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación.
 * - Repository → Encargado de obtener las series.
 * - SessionEvents → Gestiona eventos globales como navegación.
 *
 * Al inicializarse:
 * - Ejecuta automáticamente la carga del catálogo.
 *
 * Utiliza:
 * - MutableStateFlow para modificar el estado interno.
 * - StateFlow para exponer estado inmutable a la UI.
 * - viewModelScope para emitir eventos asíncronos.
 *
 * En Jetpack Compose:
 * - La UI observa uiState.
 * - Cuando cambian las series o el estado de carga,
 *   la pantalla se recompone automáticamente.
 * - Los eventos de navegación se emiten mediante SessionEvents.
 *
 * @param repository Repositorio encargado de obtener
 * el catálogo de series.
 * @param sessionEvents Objeto global para emitir
 * eventos de navegación (inyectable para testing).
 *
 * @see VideoClubOnlineSeriesUiState
 * @see ISeriesRepository
 * @see SessionEvents
 * @see RouteNavigation
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 */
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
            onError = {
                _uiState.update { it.copy(isLoading = false) }
            },
            onSuccess = { series ->
                val agrupadas = series.groupBy { it.categoria }
                _uiState.update {
                    it.copy(
                        series = series,
                        seriesPorCategoria = agrupadas,
                        isLoading = false
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


    // FUNCIÓN: cuando el usuario toca una serie

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
