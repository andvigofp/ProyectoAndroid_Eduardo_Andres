package com.example.proyecto_eduardo_andres.viewData.searchSeriesData

data class SearchDataPeliculas(
    val query: String = "",
    val seriesFiltradas: List<VideoClubOnlineSearchSeriesData> = emptyList()
)
