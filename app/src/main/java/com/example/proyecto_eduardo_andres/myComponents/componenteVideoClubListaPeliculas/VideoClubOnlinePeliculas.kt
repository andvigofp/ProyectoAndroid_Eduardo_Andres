package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.myComponents.componenteMenu.VideoClubMenuDrawer
import com.example.proyecto_eduardo_andres.myComponents.toolBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubOnlinePeliculas() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        // --- CONTENIDO PRINCIPAL ---
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Espacio superior
            item { Spacer(modifier = Modifier.height(12.dp)) }

            // Lista de películas agrupadas por categoría
            val peliculasData = PeliculasDataClass()
            val categoriasAgrupadas = peliculasData.nombrePeliculas.groupBy { it.nombreCategoria }

            categoriasAgrupadas.forEach { (categoria, peliculas) ->
                item(key = categoria) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Título de la categoría
                        Text(
                            text = categoria,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                        )

                        // Scroll horizontal de películas
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
                                            .clickable { /* onPeliculaClick(pelicula) */ },
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

            // Espacio inferior
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubOnlineScreenPreview() {
    MaterialTheme {
        VideoClubOnlinePeliculas()
    }
}
