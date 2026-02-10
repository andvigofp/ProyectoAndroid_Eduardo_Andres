package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository

import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

// Implementación en memoria ligera para usar en Previews sin Context
class AlquilerSearchSeriesRepositoryInMemory : IAlquilerSearchSeriesRepository {
    override fun obtenerSeriesSearch(
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

