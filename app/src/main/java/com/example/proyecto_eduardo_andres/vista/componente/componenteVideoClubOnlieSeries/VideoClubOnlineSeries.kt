package com.example.proyecto_eduardo_andres.vista.componente.componenteVideoClubOnlieSeries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 * @author Eduardo
 * @see Componenente Lista de series
 * @param categorias: Lista de series correspondiente por su categoría
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubOnlineSeries(
    seriesPorCategoria: Map<Int, List<VideoClubOnlineSeriesData>>,
    onSerieClick: (nombreSerie: Int) -> Unit
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
                            SerieItem(
                                serie = serie,
                                onClick = { clickedSerie ->
                                    onSerieClick(clickedSerie)

                                }
                            )
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
        val series = SeriesDto().series
        val seriesPorCategoria = series.groupBy { it.categoria }
        VideoClubOnlineSeries(
            seriesPorCategoria = seriesPorCategoria,
            onSerieClick = {}
        )
    }
}