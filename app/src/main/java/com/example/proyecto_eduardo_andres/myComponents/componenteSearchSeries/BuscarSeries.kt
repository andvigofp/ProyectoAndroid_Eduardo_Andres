package com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries

import com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubOnlieSeries.VideoClubOnlineData

/**
 * @author Andrés
 * @see Componenente Buscar la serie de la lista
 * @param peliculas: Busca la serie de la lista por nombre
 */
fun buscarPeliculas(
    series: List<VideoClubOnlineData>,
    query: String
): List<VideoClubOnlineData> {
    return series.filter { it.nombreSerie.contains(query, ignoreCase = true) }
}
