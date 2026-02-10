package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

interface IAlquilerSearchSeriesRepository {
    fun obtenerSeriesSearch(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )
}

