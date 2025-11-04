package com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas

import com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas.VideoClubOnlinePeliculasData

fun buscarPeliculas(
    peliculas: List<VideoClubOnlinePeliculasData>,
    query: String
): List<VideoClubOnlinePeliculasData> {
    return peliculas.filter { it.nombrePelicula.contains(query, ignoreCase = true) }
}
