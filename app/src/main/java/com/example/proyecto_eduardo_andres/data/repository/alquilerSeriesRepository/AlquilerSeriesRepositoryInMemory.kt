package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.PeliculasDto
import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData


class AlquilerSeriesRepositoryInMemory : IAlquilerSeriesRepository {

    private val alquileres = mutableMapOf<String, MutableList<VideoClubOnlineSeriesData>>() // <-- String

    override fun alquilarSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val seriesUsuario = alquileres.getOrPut(userId) { mutableListOf() }
            seriesUsuario.add(serie)
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun devolverSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            alquileres[userId]?.remove(serie)
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun obtenerEstadoAlquiler(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    override fun obtenerSeriesAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        try {
            val seriesUsuario = alquileres[userId] ?: emptyList()
            onSuccess(seriesUsuario)
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit,
    ) {
        try {
            val series = SeriesDto().series
            onSuccess(series)
        } catch (e: Throwable) {
            onError(e)
        }
    }


}
