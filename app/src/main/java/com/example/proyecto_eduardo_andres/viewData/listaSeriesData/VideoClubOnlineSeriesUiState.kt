package com.example.proyecto_eduardo_andres.viewData.listaSeriesData

import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasData

data class VideoClubOnlineSeriesUiState(
    val series: List<VideoClubOnlineSeriesData> = emptyList(),
    val seriesPorCategoria: Map<Int, List<VideoClubOnlineSeriesData>> = emptyMap()
)
