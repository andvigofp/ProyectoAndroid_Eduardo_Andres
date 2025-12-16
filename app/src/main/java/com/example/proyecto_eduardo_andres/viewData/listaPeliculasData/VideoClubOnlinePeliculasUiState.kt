package com.example.proyecto_eduardo_andres.viewData.listaPeliculasData

data class VideoClubOnlinePeliculasUiState(
    val peliculas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val peliculasPorCategoria: Map<Int, List<VideoClubOnlinePeliculasData>> = emptyMap()
)
