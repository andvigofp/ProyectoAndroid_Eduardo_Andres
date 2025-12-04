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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData.AlquilarDevolverPeliculasData
import com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas.AlquilerDevolverPeliculas
import com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas.BotonAlquilarPeliculas
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData.PeliculasAlquilarDevolverData
import com.example.proyecto_eduardo_andres.myComponents.componenteCustomScreenPeliculasSeries.CustomScreenWithoutScaffold
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar
import com.example.proyecto_eduardo_andres.naveHost.AppScreens
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonData
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlquilarDevolverPeliculasScreen(navController: NavController) {

    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(
            colorVioleta,
            colorAzulOscurso
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    val listaPeliculas = PeliculasAlquilarDevolverData().nombrePeliculas
    val peliculaSeleccionada = listaPeliculas.firstOrNull {
        it.nombrePelicula == R.string.la_vida_es_bella
    } ?: listaPeliculas.first()

    val peliculaDemo = AlquilarDevolverPeliculasData(
        imagen = peliculaSeleccionada.imagen,
        nombrePelicula = peliculaSeleccionada.nombrePelicula,
        descripcion = peliculaSeleccionada.descripcion
    )

    CustomScreenWithoutScaffold(

        // ---------- TOP BAR ----------
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(toolbarBackGround)   // ← degradado cubre TODO, incluso reloj
            ) {
                Column(
                    modifier = Modifier.statusBarsPadding() // solo mueve el contenido
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    toolBar(
                        onBackClick = { navController.popBackStack() },
                        onHomeClick = { navController.navigate(AppScreens.VideoClubPeliculas.routeId.toString()) },
                        onCameraClick = { navController.navigate(AppScreens.Camara.routeId.toString()) },
                        onProfileClick = { navController.navigate(AppScreens.PerfilUsuario.routeId.toString()) },
                        onLogoutClick = { navController.navigate(AppScreens.Login.routeId.toString()) }
                    )
                }
            }
        },

        // ---------- BOTTOM BAR ----------
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(toolbarBackGround)
            )
        }
    ) {

        // ---------- CONTENIDO PRINCIPAL ----------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.alquiler_peliculas),
                style = typography.headlineLarge.copy(color = colors.primary),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            AlquilerDevolverPeliculas(peliculas = peliculaDemo)
        }

        // ---------- BOTONES ----------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            BotonAlquilarPeliculas(
                botonAlquilar = ButtonData(nombre = R.string.alquilar, type = ButtonType.PRIMARY),
                botonDevolver = ButtonData(nombre = R.string.devolver, type = ButtonType.SECONDARY)
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilarDevolverPeliculasScreenPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        AlquilarDevolverPeliculasScreen(navController)
    }
}