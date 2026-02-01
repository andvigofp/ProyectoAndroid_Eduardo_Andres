@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteSearchPeliculas

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 * @author Eduardo
 * @see Componenente Buscar la pelicula  de la lista
 * @param peliculas: Busca la pelicula de la lista por nombre
 */
@Composable
fun buscarPeliculas(
    peliculas: List<VideoClubOnlinePeliculasData>,
    query: String
): List<VideoClubOnlinePeliculasData> {
    return peliculas.filter {
        stringResource(id = it.nombre).contains(query, ignoreCase = true)
    }
}
