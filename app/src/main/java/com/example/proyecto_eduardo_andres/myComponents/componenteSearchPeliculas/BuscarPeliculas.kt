package com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas

fun buscarPeliculas(
    peliculas: List<VideoClubOnlineSearchPeliculasData>,
    query: String
): List<VideoClubOnlineSearchPeliculasData> {
    return peliculas.filter { it.nombrePelicula.contains(query, ignoreCase = true) }
}
