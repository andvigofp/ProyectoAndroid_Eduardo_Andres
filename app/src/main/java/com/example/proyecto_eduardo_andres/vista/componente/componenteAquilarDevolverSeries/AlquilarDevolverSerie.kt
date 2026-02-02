@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteAquilarDevolverSeries

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewmodel.ustate.AlquilarDevolverSeriesUiState
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData

/**
 * @author Andrés
 * @see Componenente imagen descripción
 * @param series: Listado de peliculas a mostrar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlquilarDevolverSerie(
    series: AlquilarDevolverSeriesUiState
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(8.dp)
    ) {
        // Contenedor principal
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Imagen o icono a la izquierda
            if (series.imagen != null) {
                Image(
                    painter = painterResource(id = series.imagen!!),
                    contentDescription = stringResource( series.nombreSerie),
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, colors.outline, RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Movie,
                    contentDescription = R.string.icono_serie.toString(),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp),
                    tint = colors.onBackground
                )
            }

            // Nombre y descripción a la derecha
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(series.nombreSerie),
                    style = typography.headlineMedium.copy(color = colors.primary),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(series.descripcion),
                    style = typography.bodyLarge.copy(color = colors.primary),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

/**
 * @author Andrés
 * Componenente imagen descripción
 * @param series: AlquilarDevolverSeriesUiState
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilarDevolverSeriePreview() {

    val serieDemo = AlquilarDevolverSeriesUiState(
        serie = VideoClubOnlineSeriesData(
            id = "serie_001",
            categoria = R.string.drama,
            imagen = R.drawable.ic_mad_men,
            nombre = R.string.mad_men,
            descripcion = R.string.vida_don_draper
        ),
        serieAlquilada = false,
        fechaAlquiler = null,
        fechaDevolucion = null
    )

    MaterialTheme {
        AlquilarDevolverSerie(series = serieDemo)
    }
}

