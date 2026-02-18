package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 * @author Andrés
 * @see VideoClubOnlineSearchPeliculasUiState
 *
 * @param UIState para la búsqueda de películas.
 *
 * @param query Término de búsqueda.
 *
 * @param peliculas Lista completa de películas.
 * @param peliculasFiltradas Lista de películas filtradas.
 * @param isLoading Indica si la operación de búsqueda de películas está en proceso.
 * @param error Mensaje de error en caso de que ocurra.
 *
 */
data class VideoClubOnlineSearchPeliculasUiState(
    val query: String = "",
    val peliculas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val peliculasFiltradas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val isLoading: Boolean = true
)