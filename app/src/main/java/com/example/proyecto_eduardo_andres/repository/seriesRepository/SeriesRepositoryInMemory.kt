package com.example.proyecto_eduardo_andres.repository.seriesRepository

import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.SeriesData
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData

class SeriesRepositoryInMemory : ISeriesRepository {
    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        try {
            val series = SeriesData().series
            onSuccess(series)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}

