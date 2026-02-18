package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 * @author Andrés
 * @see VideoClubOnlinePeliculasUiState
 *
 * @param UIState para el catálogo de películas.
 *
 * @param peliculas Lista de películas.
 * @param peliculasPorCategoria Mapa que agrupa las películas por categoría.
 * @param isLoading Indica si la operación de obtención de películas está en proceso.
 * @param error Mensaje de error en caso de que ocurra.
 *
 * @see VideoClubOnlinePeliculasData
 */
data class VideoClubOnlinePeliculasUiState(
    val peliculas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val peliculasPorCategoria: Map<Int, List<VideoClubOnlinePeliculasData>> = emptyMap(),
    val isLoading: Boolean = true
)