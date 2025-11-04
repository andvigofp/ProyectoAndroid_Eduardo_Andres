package com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlquilerDevolverPeliculas(
    peliculas: AlquilarDevolverPeliculasData
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
            if (peliculas.imagen != null) {
                Image(
                    painter = painterResource(id = peliculas.imagen),
                    contentDescription = peliculas.nombrePelicula,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, colors.outline, RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Movie,
                    contentDescription = "Icono película",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp),
                    tint = colors.onBackground
                )
            }

            // Nombre y descripción a la derecha
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = peliculas.nombrePelicula,
                    style = typography.headlineMedium.copy(color = colors.primary),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = peliculas.descripcion,
                    style = typography.bodyLarge.copy(color = colors.primary),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilarDevolverPeliculasPreview() {
    val listaPeliculas = PeliculasAlquilarDevolverData().nombrePeliculas
    val peliculaSeleccionada = listaPeliculas.firstOrNull { it.nombrePelicula == "La Vida es Bella" }
        ?: listaPeliculas.first()

    // Se crea correctamente el objeto del tipo de data class
    val peliculaDemo = AlquilarDevolverPeliculasData(
        imagen = peliculaSeleccionada.imagen, // se pasa la imagen
        nombrePelicula = peliculaSeleccionada.nombrePelicula,
        descripcion = peliculaSeleccionada.descripcion
    )

    // Se llama al composable correcto
    MaterialTheme {
        AlquilerDevolverPeliculas(peliculas = peliculaDemo)
    }
}
