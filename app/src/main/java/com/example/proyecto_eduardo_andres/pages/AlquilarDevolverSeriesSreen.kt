@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.AlquilarDevolverSerie
import com.example.proyecto_eduardo_andres.viewData.AlquilerDevolverSeriesData.AlquilarDevolverSerieData
import com.example.proyecto_eduardo_andres.viewData.AlquilerDevolverSeriesData.BotonAlquilarDevolverData
import com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries.BotonAlquilarSeries
import com.example.proyecto_eduardo_andres.myComponents.componenteCustomScreenPeliculasSeriesWithoutScaffold.CustomScreenWithoutScaffold
import com.example.proyecto_eduardo_andres.viewData.AlquilerDevolverSeriesData.SeriesAlquilerDevolverData
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlquilerDevolverSeriesScreen() {

    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    // Degradado del toolbar y fondo del reloj
    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(
            colorVioleta,
            colorAzulOscurso
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    // Recuperar los datos reales desde la lista
    val listaSeries = SeriesAlquilerDevolverData().nombreSeries
    val serieSeleccionada = listaSeries.firstOrNull {
        it.nombreSerie == R.string.mad_men
    } ?: listaSeries.first()

    // Crear objeto de tipo AlquilarDevolverSerieData
    val serieDemo = AlquilarDevolverSerieData(
        imagen = serieSeleccionada.imagen,
        nombreSerie = serieSeleccionada.nombreSerie,
        descripcion = serieSeleccionada.descripcion
    )

    // Usamos tu contenedor personalizado
    CustomScreenWithoutScaffold(
        // ---------------- TOP BAR ----------------
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(toolbarBackGround)
                    .statusBarsPadding()
            ) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
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

        // ---------------- BOTTOM BAR ----------------
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(toolbarBackGround)
            )
        }
    ) {

        // ---------------- CONTENIDO PRINCIPAL ----------------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Título centrado
            Text(
                text = stringResource(R.string.aqlqilar_serie),
                style = typography.headlineLarge.copy(color = colors.primary),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Imagen + descripción
            AlquilarDevolverSerie(series = serieDemo)
        }

        // ---------------- BOTONES (encima del bottom bar) ----------------
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilerDevolverSeriesScreenPreview() {
    MaterialTheme {
        AlquilerDevolverSeriesScreen()
    }
}