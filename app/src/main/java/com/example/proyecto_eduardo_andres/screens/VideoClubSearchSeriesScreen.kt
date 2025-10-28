package com.example.proyecto_eduardo_andres.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries.SearchBar
import com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries.SeriesDataClass
import com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries.buscarPeliculas
import com.example.proyecto_eduardo_andres.myComponents.toolBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubSearchSeriesScreen() {
    val seriesData = SeriesDataClass()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val seriesFiltrada = buscarPeliculas(seriesData.nombreSeries, searchQuery.text)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // color de fondo bonito
    ) {
        // --- TOOLBAR SUPERIOR con espacio superior ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding() // respeta la barra de estado
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color(0xFF0D47A1), Color(0xFF512DA8))
                    )
                )
                .padding(top = 12.dp, start = 12.dp, bottom = 12.dp)
        ) {
            toolBar(
                onHomeClick = { /* acción home */ },
                onCameraClick = { /* acción cámara */ },
                onProfileClick = { /* acción perfil */ },
                onLogoutClick = { /* acción logout */ }
            )
        }

        // --- CONTENIDO: Búsqueda y Lista ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onQueryChange = { searchQuery = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- Lista de películas con scroll vertical ---
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(seriesFiltrada) { serie ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFBBDEFB), RoundedCornerShape(12.dp)) // tarjeta azul suave
                            .padding(8.dp)
                    ) {
                        if (serie.imagen != null) {
                            Image(
                                painter = painterResource(id = serie.imagen),
                                contentDescription = serie.nombreSerie,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(Color.Gray, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Img", color = Color.White, fontSize = 12.sp)
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = serie.nombreSerie,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                            Text(
                                text = serie.nombreCategoria,
                                fontSize = 12.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubSearchScreenSeriePreview() {
    VideoClubSearchSeriesScreen()
}