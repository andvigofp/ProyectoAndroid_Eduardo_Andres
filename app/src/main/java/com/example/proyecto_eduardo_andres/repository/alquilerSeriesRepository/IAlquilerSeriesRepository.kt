package com.example.proyecto_eduardo_andres.repository.alquilerSeriesRepository

import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData

interface IAlquilerSeriesRepository {

    fun alquilarSerie(
        userId: Int,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )

    fun devolverSerie(
        userId: Int,
        serie: VideoClubOnlineSeriesData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )
}
