@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteSearchSeries

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 * @author Andrés
 * @see Componenente Buscar la serie de la lista
 * @param peliculas: Busca la serie de la lista por nombre
 */
@Composable
fun buscarSeries(
    series: List<VideoClubOnlineSeriesData>,
    query: String
): List<VideoClubOnlineSeriesData> {
    return series.filter { serie ->
        stringResource(id = serie.nombre).contains(query, ignoreCase = true)
    }
}

