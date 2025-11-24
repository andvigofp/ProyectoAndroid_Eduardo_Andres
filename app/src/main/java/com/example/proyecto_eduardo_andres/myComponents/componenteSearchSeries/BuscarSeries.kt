@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.proyecto_eduardo_andres.viewData.ListaSeriesData.VideoClubOnlineData

/**
 * @author Andrés
 * @see Componenente Buscar la serie de la lista
 * @param peliculas: Busca la serie de la lista por nombre
 */
@Composable
fun buscarSeries(
    series: List<VideoClubOnlineData>,
    query: String
): List<VideoClubOnlineData> {
    return series.filter { serie ->
        stringResource(id = serie.nombreSerie).contains(query, ignoreCase = true)
    }
}

