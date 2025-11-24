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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewData.ListaSeriesData.SeriesData

/**
 * @author Eduardo
 * @see Componenente Lista de series
 * @param categorias: Lista de series correspondiente por su categoría
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubOnlineSeries() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        // --- CONTENIDO PRINCIPAL ---
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Espacio superior
            item { Spacer(modifier = Modifier.height(12.dp)) }

            // Lista de series agrupadas por categoría
            val seriesData = SeriesData()
            val categoriasAgrupadas = seriesData.nombreSeries.groupBy { it.nombreCategoria }

            categoriasAgrupadas.forEach { (categoria, series) ->
                item(key = categoria) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Título de la categoría
                        Text(
                            text = stringResource(categoria),
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                        )

                        // Scroll horizontal de series
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(series.size) { index ->
                                val serie = series[index]

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(6.dp),
                                    modifier = Modifier.width(130.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(130.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(MaterialTheme.colorScheme.surfaceVariant)
                                            .clickable { /* onSerieClick(serie) */ },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        serie.imagen?.let {
                                            Image(
                                                painter = painterResource(id = it),
                                                contentDescription = stringResource(serie.nombreSerie),
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        } ?: Icon(
                                            imageVector = Icons.Default.Movie,
                                            contentDescription = stringResource(R.string.sin_imagen),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(48.dp)
                                        )
                                    }

                                    Text(
                                        text = stringResource(serie.nombreSerie),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = MaterialTheme.colorScheme.onBackground,
                                            fontWeight = FontWeight.Medium,
                                            textAlign = TextAlign.Center
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Espacio inferior
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubOnlineSeriesPreview() {
    MaterialTheme {
        VideoClubOnlineSeries()
    }
}