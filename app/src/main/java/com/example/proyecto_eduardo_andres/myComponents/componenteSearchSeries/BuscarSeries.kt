package com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries

fun buscarPeliculas(
    series: List<VideoClubOnlineSearchSeriesData>,
    query: String
): List<VideoClubOnlineSearchSeriesData> {
    return series.filter { it.nombreSerie.contains(query, ignoreCase = true) }
}
