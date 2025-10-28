package com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas

fun buscarPeliculas(
    peliculas: List<VideoClubOnlineDataClass>,
    query: String
): List<VideoClubOnlineDataClass> {
    return peliculas.filter { it.nombrePelicula.contains(query, ignoreCase = true) }
}
