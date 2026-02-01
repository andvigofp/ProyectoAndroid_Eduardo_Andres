package com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData


class AlquilerSeriesRepositoryInMemory :
    com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.IAlquilerSeriesRepository {

    private val alquileres =
        mutableMapOf<Int, MutableList<VideoClubOnlineSeriesData>>()

    override fun alquilarSerie(
        userId: Int,
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
        userId: Int,
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
        userId: Int,
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
