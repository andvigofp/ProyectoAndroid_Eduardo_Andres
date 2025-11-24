package com.example.proyecto_eduardo_andres.viewData.SearchPeliculasData

// Data class para manejar la búsqueda
data class SearchData(
    val query: String = "",
    val peliculasFiltradas: List<VideoClubOnlineSearchPeliculasData> = emptyList()
)
