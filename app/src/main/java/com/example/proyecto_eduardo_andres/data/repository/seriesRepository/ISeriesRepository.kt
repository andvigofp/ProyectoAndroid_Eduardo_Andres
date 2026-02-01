package com.example.proyecto_eduardo_andres.data.repository.seriesRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

interface ISeriesRepository {
    fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )
}



