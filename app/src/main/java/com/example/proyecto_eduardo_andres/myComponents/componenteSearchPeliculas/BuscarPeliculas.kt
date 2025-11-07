package com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas

import com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas.VideoClubOnlinePeliculasData

/**
 * @author Eduardo
 * @see Componenente Buscar la pelicula  de la lista
 * @param peliculas: Busca la pelicula de la lista por nombre
 */
fun buscarPeliculas(
    peliculas: List<VideoClubOnlinePeliculasData>,
    query: String
): List<VideoClubOnlinePeliculasData> {
    return peliculas.filter { it.nombrePelicula.contains(query, ignoreCase = true) }
}
