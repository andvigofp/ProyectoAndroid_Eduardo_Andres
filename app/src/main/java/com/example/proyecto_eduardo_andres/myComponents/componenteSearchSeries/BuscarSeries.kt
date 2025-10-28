package com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries

fun buscarPeliculas(
    series: List<VideoClubOnlineDataClass>,
    query: String
): List<VideoClubOnlineDataClass> {
    return series.filter { it.nombreSerie.contains(query, ignoreCase = true) }
}
