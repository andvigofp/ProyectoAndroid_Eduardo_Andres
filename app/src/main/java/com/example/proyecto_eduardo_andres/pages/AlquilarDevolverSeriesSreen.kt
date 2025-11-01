package com.example.proyecto_eduardo_andres.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.AlquilarDevolverSerie
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.AlquilarDevolverSerieData
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.BotonAlquilarDevolverData
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.BotonAlquilarSeries
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlquilerDevolverSeriesScreen() {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    // Degradado del toolbar para el status bar
    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(androidx.compose.ui.graphics.Color(0xFF0D47A1), androidx.compose.ui.graphics.Color(0xFF512DA8)),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    Scaffold(
        // Status bar y toolbar con el mismo color
        topBar = {
            // Box que envuelve status bar y toolbar con el mismo color
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(toolbarBackGround)
                    .statusBarsPadding()
            ) {
                Column {
                    // Espacio para el status bar
                    Spacer(modifier = Modifier.height(8.dp))
                    // Toolbar
                    toolBar(
                        onBackClick = {},
                        onHomeClick = {},
                        onCameraClick = {},
                        onProfileClick = {},
                        onLogoutClick = {}
                    )
                }
            }
        },
        // BottomBar vacío (solo para reservar el espacio visual)
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(colors.primaryContainer)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Contenido principal (serie con imagen y descripción) - Arriba
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp) // Margen superior para separar del toolbar
                    .align(Alignment.TopStart),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título "ALQUILAR SERIES" en mayúsculas y centrado
                Text(
                    text = "ALQUILAR SERIES",
                    style = typography.headlineLarge,
                    color = colors.primary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                
                AlquilarDevolverSerie(
                    series = AlquilarDevolverSerieData(
                        nombreSerie = "Mad Men",
                        descripcion = "Ambientada en los años 60, esta serie sigue la vida de Don Draper, un brillante publicista con una vida personal turbulenta."
                    )
                )
            }

            // Botones de Alquilar / Devolver - Abajo, pegados al BottomBar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                BotonAlquilarSeries(
                    botonAlquilar = BotonAlquilarDevolverData("Alquilar"),
                    botonDevolver = BotonAlquilarDevolverData("Devolver")
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilerDevolverSeriesScreenPreview() {
    MaterialTheme {
        AlquilerDevolverSeriesScreen()
    }
}
