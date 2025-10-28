package com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas

// Data class para manejar la búsqueda
data class SearchDataClass(
    val query: String = "",
    val peliculasFiltradas: List<VideoClubOnlineDataClass> = emptyList()
)
