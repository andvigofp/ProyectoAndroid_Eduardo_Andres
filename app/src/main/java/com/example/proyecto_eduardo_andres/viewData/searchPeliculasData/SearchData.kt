package com.example.proyecto_eduardo_andres.viewData.searchPeliculasData

// Data class para manejar la búsqueda
data class SearchDataPeliculas(
    val query: String = "",
    val peliculasFiltradas: List<VideoClubOnlineSearchPeliculasData> = emptyList()
)
