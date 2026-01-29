package com.example.proyecto_eduardo_andres.repository.seriesRepository

import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData

interface ISeriesRepository {
    fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )
}



