package com.example.proyecto_eduardo_andres.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverSeriesData.VideoClubOnlineAlquilarSeriesUiState
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
}
