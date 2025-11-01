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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas.AlquilarDevolverPeliculasData
import com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas.AlquilerDevolverPeliculas
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.BotonAlquilarDevolverData
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.BotonAlquilarSeries
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar

@Composable
fun AlquilarDevolverPeliculasScreen() {
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
                    text = "ALQUILAR PELICULAS",
                    style = typography.headlineLarge,
                    color = colors.primary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                AlquilerDevolverPeliculas(
                    peliculas = AlquilarDevolverPeliculasData(
                        nombrePelicula = "La vida es bella",
                        descripcion = "Un padre usa el humor para proteger a su hijo en un campo de concentración."
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
fun AlquilerDevolverPeliculasScreenPreview() {
    MaterialTheme {
        AlquilarDevolverPeliculasScreen()
    }
}
