package com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AlquilarDevolverSerie(
    series: AlquilarDevolverSerieData
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Contenedor principal
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Imagen / icono a la izquierda
                    Icon(
                        imageVector = Icons.Default.Movie,
                        contentDescription = "Icono película",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(8.dp),
                        tint = colors.onBackground
                    )

                    // Nombre y descripción a la derecha
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = series.nombreSerie,
                            style = typography.headlineMedium.copy(color = colors.primary),
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = series.descripcion,
                            style = typography.bodyLarge.copy(color = colors.primary),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilarDevolverSeriePreview() {
    val listaSeries = SeriesData().nombreSeries
    val serieSeleccionada = listaSeries.firstOrNull { it.nombreSerie == "Mad Men" }
        ?: listaSeries.first()

    val serieDemo = AlquilarDevolverSerieData(
        nombreSerie = serieSeleccionada.nombreSerie,
        descripcion = serieSeleccionada.descripcion
    )

    MaterialTheme {
        AlquilarDevolverSerie(series = serieDemo)
    }
}
