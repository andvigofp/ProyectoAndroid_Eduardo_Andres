package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubOnline

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VideoClubOnlinePeliculas(
    peliculasData: PeliculasDataClass = PeliculasDataClass(),
    onPeliculaClick: (VideoClubOnlineDataClass) -> Unit
) {
    val categoriasAgrupadas = peliculasData.nombrePeliculas.groupBy { it.nombreCategoria }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 70.dp, bottom = 100.dp, start = 16.dp, end = 16.dp), // margen superior e inferior mayores
        verticalArrangement = Arrangement.spacedBy(45.dp) // más separación entre categorías
    ) {
        categoriasAgrupadas.forEach { (categoria, peliculas) ->
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // --- Título de la categoría ---
                    Text(
                        text = categoria,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    // --- Scroll horizontal de películas ---
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(peliculas.size) { index ->
                            val pelicula = peliculas[index]

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(6.dp),
                                modifier = Modifier.width(130.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(130.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFE3F2FD))
                                        .clickable { onPeliculaClick(pelicula) },
                                    contentAlignment = Alignment.Center
                                ) {
                                    pelicula.imagen?.let {
                                        Image(
                                            painter = painterResource(id = it),
                                            contentDescription = pelicula.nombrePelicula,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    } ?: Icon(
                                        imageVector = Icons.Default.Movie,
                                        contentDescription = "Sin imagen",
                                        tint = Color.Gray,
                                        modifier = Modifier.size(48.dp)
                                    )
                                }

                                Text(
                                    text = pelicula.nombrePelicula,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubPeliculasPreview() {
    MaterialTheme {
        VideoClubOnlinePeliculas (
            peliculasData = PeliculasDataClass(),
            onPeliculaClick = {}
        )
    }
}