package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

data class VideoClubOnlineSearchPeliculasUiState(
    val query: String = "",
    val peliculas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val peliculasFiltradas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val error: String? = null
)