package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 * @author Eduardo
 * @see VideoClubOnlineSearchSeriesUiState
 *
 * @param UIState para la búsqueda de series.
 *
 * @param query Término de búsqueda.
 *
 * @param series Lista completa de series.
 * @param seriesFiltradas Lista de series filtradas.
 * @param isLoading Indica si la operación de búsqueda de series está en proceso.
 * @param error Mensaje de error en caso de que ocurra.
 *
 */
data class VideoClubOnlineSearchSeriesUiState(
    val query: String = "",
    val series: List<VideoClubOnlineSeriesData> = emptyList(),
    val seriesFiltradas: List<VideoClubOnlineSeriesData> = emptyList(),
    val isLoading: Boolean = true
)