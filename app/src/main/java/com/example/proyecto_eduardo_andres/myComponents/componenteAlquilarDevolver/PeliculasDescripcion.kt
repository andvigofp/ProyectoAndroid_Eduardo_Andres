package com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeliculasDescripcion(
    pelicula: AlquilarDevolverPeliculaDataClass,
    onAlquilarClick: () -> Unit,
    onDevolverClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography



    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Contenedor lila con icono arriba y descripción abajo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f) // Ocupa la mayor parte de la pantalla
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Icono arriba
                    Icon(
                        imageVector = Icons.Default.Movie,
                        contentDescription = "Icono película",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(bottom = 16.dp),
                        tint = colors.primary
                    )

                    // Descripción abajo
                    Text(
                        text = pelicula.descripcion,
                        style = typography.bodyLarge.copy(color = colors.primary),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Botones (abajo)
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = onAlquilarClick,
                    colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Alquilar", style = typography.labelLarge.copy(color = colors.onPrimary))
                }

                Button(
                    onClick = onDevolverClick,
                    colors = ButtonDefaults.buttonColors(containerColor = colors.errorContainer),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Devolver",
                        style = typography.labelLarge.copy(color = colors.onErrorContainer)
                    )
                }
            }
        }
    }
}

// ---------------------------
// 🧩 PREVIEW
// ---------------------------
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPeliculasDescripcion() {
    val listaPeliculas = PeliculasDataClass().nombrePeliculas
    val peliculaSeleccionada = listaPeliculas.firstOrNull { it.nombrePelicula == "La Vida es Bella" }
        ?: listaPeliculas.first()

    val peliculaDemo = AlquilarDevolverPeliculaDataClass(
        nombrePelicula = peliculaSeleccionada.nombrePelicula,
        descripcion = peliculaSeleccionada.descripcion
    )

    MaterialTheme {
        PeliculasDescripcion(
            pelicula = peliculaDemo,
            onAlquilarClick = {},
            onDevolverClick = {}
        )
    }
}