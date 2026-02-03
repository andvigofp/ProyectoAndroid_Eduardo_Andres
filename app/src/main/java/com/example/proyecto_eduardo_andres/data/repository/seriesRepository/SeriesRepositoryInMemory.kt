package com.example.proyecto_eduardo_andres.data.repository.seriesRepository

import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

class SeriesRepositoryInMemory :
    ISeriesRepository {
    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        try {
            val series = SeriesDto().series
            onSuccess(series)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}



