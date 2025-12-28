package com.example.proyecto_eduardo_andres.repository.AlquilerSeriesRepository

import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverSeriesData.VideoClubOnlineAlquilarSeriesUiState

interface IAlquilerSeriesRepository {
    fun alquilarSerie(
        userId: Int,
        serie: VideoClubOnlineAlquilarSeriesUiState,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    fun devolverSerie(
        userId: Int,
        serie: VideoClubOnlineAlquilarSeriesUiState,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )
}
