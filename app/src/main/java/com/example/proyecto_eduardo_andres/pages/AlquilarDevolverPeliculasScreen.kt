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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewData.AlquilerDevolverPeliculasData.AlquilarDevolverPeliculasData
import com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas.AlquilerDevolverPeliculas
import com.example.proyecto_eduardo_andres.viewData.AlquilerDevolverPeliculasData.PeliculasAlquilarDevolverData
import com.example.proyecto_eduardo_andres.viewData.AlquilerDevolverSeriesData.BotonAlquilarDevolverData
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.BotonAlquilarSeries
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlquilarDevolverPeliculasScreen() {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    //Degradado del toolbar y fondo del reloj
    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surfaceContainerHigh,
            MaterialTheme.colorScheme.surfaceContainerHighest
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    //Recuperar los datos reales desde la lista
    val listaPeliculas = PeliculasAlquilarDevolverData().nombrePeliculas
    val peliculaSeleccionada = listaPeliculas.firstOrNull { it.nombrePelicula == R.string.la_vida_es_bella }
        ?: listaPeliculas.first()

    //Crear objeto de tipo AlquilarDevolverPeliculasData
    val peliculaDemo = AlquilarDevolverPeliculasData(
        imagen = peliculaSeleccionada.imagen,
        nombrePelicula = peliculaSeleccionada.nombrePelicula,
        descripcion = peliculaSeleccionada.descripcion,
    )

    Scaffold(
        //Toolbar superior (degradado + espacio del reloj)
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(toolbarBackGround)
                    .statusBarsPadding()
            ) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp)) // espacio bajo el reloj
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
        //BottomBar solo decorativa
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(toolbarBackGround)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colors.background)
        ) {
            //Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                //Título
                Text(
                    text = stringResource (R.string.alquiler_peliculas),
                    style = typography.headlineLarge.copy(color = colors.primary),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                //Componente principal (usa la imagen y descripción del data class)
                AlquilerDevolverPeliculas(peliculas = peliculaDemo)
            }

            //Botones de Alquilar / Devolver — encima del BottomBar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                BotonAlquilarSeries(
                    botonAlquilar = BotonAlquilarDevolverData(R.string.alquilar),
                    botonDevolver = BotonAlquilarDevolverData(R.string.devolver)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilarDevolverPeliculasScreenPreview() {
    MaterialTheme {
        AlquilarDevolverPeliculasScreen()
    }
}