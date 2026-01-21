package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.PeliculasData
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasData

/**
 * @author Andres
 * @see Componenente Lista de peliculas
 * @param categorias: Lista de peliculas correspondiente por su categoría
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubOnlinePeliculas(
    peliculasPorCategoria: Map<Int, List<VideoClubOnlinePeliculasData>>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),  // Espacio para el status bar
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        peliculasPorCategoria.forEach { (categoria, peliculas) ->
            item(key = categoria) {
                Column {

                    Text(
                        text = stringResource(categoria),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)  // Espacio abajo para separar del LazyRow
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        items(peliculas) { pelicula ->
                            PeliculaItem(pelicula)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubOnlineScreenPreview() {
    MaterialTheme {
        // Datos de ejemplo para preview
        val peliculas = PeliculasData().peliculas
        val peliculasPorCategoria = peliculas.groupBy { it.categoria }

        VideoClubOnlinePeliculas(
            peliculasPorCategoria = peliculasPorCategoria
        )
    }
}
