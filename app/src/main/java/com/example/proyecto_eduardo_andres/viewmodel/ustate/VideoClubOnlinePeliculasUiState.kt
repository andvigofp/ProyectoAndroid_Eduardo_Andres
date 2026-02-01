package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

data class VideoClubOnlinePeliculasUiState(
    val peliculas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val peliculasPorCategoria: Map<Int, List<VideoClubOnlinePeliculasData>> = emptyMap()
)