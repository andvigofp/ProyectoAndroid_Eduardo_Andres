package com.example.proyecto_eduardo_andres.viewData.searchPeliculasData

import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasData

data class VideoClubOnlineSearchPeliculasUiState(
    val query: String = "",
    val peliculas: List<VideoClubOnlinePeliculasData> = emptyList(),
    val peliculasFiltradas: List<VideoClubOnlinePeliculasData> = emptyList()
)
