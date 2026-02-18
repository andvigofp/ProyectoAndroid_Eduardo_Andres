package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 * @author Eduardo
 * @see VideoClubOnlineSeriesUiState
 *
 * @param UIState para el catálogo de series.
 *
 * @param series Lista de series.
 * @param seriesPorCategoria Mapa que agrupa las series por categoría.
 * @param isLoading Indica si la operación de obtención de series está en proceso.
 * @param error Mensaje de error en caso de que ocurra
 */
data class VideoClubOnlineSeriesUiState(
    val series: List<VideoClubOnlineSeriesData> = emptyList(),
    val seriesPorCategoria: Map<Int, List<VideoClubOnlineSeriesData>> = emptyMap(),
    val isLoading: Boolean = true
)