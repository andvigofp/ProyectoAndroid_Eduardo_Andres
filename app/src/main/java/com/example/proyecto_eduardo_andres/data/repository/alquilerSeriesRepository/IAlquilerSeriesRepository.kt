package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

interface IAlquilerSeriesRepository {

    fun alquilarSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    fun devolverSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    fun obtenerEstadoAlquiler(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    )

    fun obtenerSeriesAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )

    fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    )
}
