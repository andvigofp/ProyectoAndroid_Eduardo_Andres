package com.example.proyecto_eduardo_andres.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverSeriesData.VideoClubOnlineAlquilarSeriesUiState


class AlquilerSeriesRepositoryInMemory : IAlquilerSeriesRepository {

    private val alquileres: MutableMap<Int, MutableList<VideoClubOnlineAlquilarSeriesUiState>> = mutableMapOf()

    override fun alquilarSerie(
        userId: Int,
        serie: VideoClubOnlineAlquilarSeriesUiState,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit,
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
        serie: VideoClubOnlineAlquilarSeriesUiState,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit,
    ) {
        try {
            val seriesUsuario = alquileres[userId]
            seriesUsuario?.remove(serie)
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }
}