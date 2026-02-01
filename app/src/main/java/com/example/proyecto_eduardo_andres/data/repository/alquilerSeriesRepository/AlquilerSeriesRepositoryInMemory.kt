package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

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
}
