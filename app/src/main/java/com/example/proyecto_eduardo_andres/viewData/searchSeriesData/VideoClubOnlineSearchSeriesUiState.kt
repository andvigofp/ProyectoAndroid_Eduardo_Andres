package com.example.proyecto_eduardo_andres.viewData.searchSeriesData

import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData

data class VideoClubOnlineSearchSeriesUiState(
    val query: String = "",
    val series: List<VideoClubOnlineSeriesData> = emptyList(),
    val seriesFiltradas: List<VideoClubOnlineSeriesData> = emptyList()
)
