package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubOnlieSeries

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.PeliculasData
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.SeriesData
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData

/**
 * @author Eduardo
 * @see Componenente Lista de series
 * @param categorias: Lista de series correspondiente por su categoría
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubOnlineSeries(
    seriesPorCategoria: Map<Int, List<VideoClubOnlineSeriesData>>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(), // espacio para la status bar
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        seriesPorCategoria.forEach { (categoria, series) ->
            item(key = categoria) {
                Column {
                    Text(
                        text = stringResource(categoria),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        items(series) { serie ->
                            SerieItem(serie)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubOnlineSeriesPreview() {
    MaterialTheme {
        // Datos de ejemplo para preview
        val series = SeriesData().nombreSeries
        val seriesPorCategoria = series.groupBy { it.nombreCategoria }
        VideoClubOnlineSeries(
            seriesPorCategoria = seriesPorCategoria
        )
    }
}