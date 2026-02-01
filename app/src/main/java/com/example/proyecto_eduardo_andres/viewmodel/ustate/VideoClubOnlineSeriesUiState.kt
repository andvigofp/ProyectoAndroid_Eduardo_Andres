package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

data class VideoClubOnlineSeriesUiState(
    val series: List<VideoClubOnlineSeriesData> = emptyList(),
    val seriesPorCategoria: Map<Int, List<VideoClubOnlineSeriesData>> = emptyMap()
)