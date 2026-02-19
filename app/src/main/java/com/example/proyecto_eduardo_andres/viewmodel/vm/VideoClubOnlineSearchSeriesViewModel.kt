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

/**
 * @author Eduardo
 * @see VideoClubOnlineSearchSeriesViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de búsqueda de series.
 * - Carga el catálogo completo de series desde el repositorio.
 * - Permite filtrar dinámicamente las series según el texto introducido.
 * - Mantiene una lista original y una lista filtrada.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de filtrado y presentación.
 * - Repository → Encargado de obtener el catálogo.
 *
 * Al inicializarse:
 * - Ejecuta automáticamente la carga inicial de series.
 *
 * Utiliza:
 * - MutableStateFlow para gestionar el estado interno.
 * - StateFlow para exponer estado inmutable a la UI.
 * - update {} para mantener inmutabilidad del estado.
 *
 * En Jetpack Compose:
 * - La UI observa uiState.
 * - Cuando cambia query o seriesFiltradas,
 *   la pantalla se recompone automáticamente.
 *
 * Nota:
 * Se utiliza Context para obtener el nombre real
 * de la serie desde recursos (string resources).
 *
 * @param repository Repositorio encargado de obtener
 * el catálogo de series.
 * @param context Contexto necesario para resolver
 * recursos de tipo string.
 *
 * @see VideoClubOnlineSearchSeriesUiState
 * @see IAlquilerSearchSeriesRepository
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 * @see Context
 */
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