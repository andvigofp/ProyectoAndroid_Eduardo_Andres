package com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries

import com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubOnlieSeries.VideoClubOnlineData

fun buscarPeliculas(
    series: List<VideoClubOnlineData>,
    query: String
): List<VideoClubOnlineData> {
    return series.filter { it.nombreSerie.contains(query, ignoreCase = true) }
}
