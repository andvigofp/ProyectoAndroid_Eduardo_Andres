package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.modelo.serie
import java.util.Date
import kotlin.collections.set


class AlquilerSeriesRepositoryInMemory : IAlquilerSeriesRepository {

    private val alquileres =
        mutableMapOf<String, MutableList<VideoClubOnlineSeriesData>>()

    override fun alquilarSerie(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val seriesUsuario = alquileres.getOrPut(userId) { mutableListOf() }

            // Evitar duplicados
            if (!seriesUsuario.any { it.nombre == serie.nombre }) {
                seriesUsuario.add(serie)
            }

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
            alquileres[userId]?.removeIf { it.nombre == serie.nombre }
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun obtenerEstadoAlquiler(
        userId: String,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    ) {
        try {
            val estaAlquilada = alquileres[userId]
                ?.any { it.nombre == serie.nombre }
                ?: false

            onSuccess(
                EstadoAlquilerDto(
                    estaAlquilada = estaAlquilada,
                    fechaAlquiler = null,
                    fechaDevolucion = null
                )
            )
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun obtenerSeriesAlquiladas(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        try {
            onSuccess(alquileres[userId] ?: emptyList())
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        try {
            onSuccess(SeriesDto().series)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}