package com.example.proyecto_eduardo_andres.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.SeriesData
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData


class AlquilerSeriesRepositoryInMemory : IAlquilerSeriesRepository {

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
