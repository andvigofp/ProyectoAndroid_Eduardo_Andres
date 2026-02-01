package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

data class VideoClubOnlineSearchSeriesUiState(
    val query: String = "",
    val series: List<VideoClubOnlineSeriesData> = emptyList(),
    val seriesFiltradas: List<VideoClubOnlineSeriesData> = emptyList()
)