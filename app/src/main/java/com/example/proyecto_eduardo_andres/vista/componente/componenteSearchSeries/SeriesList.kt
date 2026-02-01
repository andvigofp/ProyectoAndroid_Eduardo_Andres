@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteSearchSeries

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.R

/**
 * @author Eduardo
 * @see Componenente Barra de busqueda
 * @param peliculas: Introduce el nombre de las series
 */
@Composable
fun SerieList(
    series: List<VideoClubOnlineSeriesData>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(series) { movie ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                if (movie.imagen != null) {
                    Image(
                        painter = painterResource(id = movie.imagen),
                        contentDescription = stringResource(movie.nombre),
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
                        Text(stringResource(R.string.img), color = Color.White, fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = stringResource(movie.nombre), fontSize = 20.sp, color = Color.Black)
                    Text(text = stringResource(movie.categoria), fontSize = 12.sp, color = Color.DarkGray)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VideoClubScreenPreview() {
    val seriesData = SeriesDto()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    //Llamada a buscarSeries dentro del composable
    val seriesFiltrada = buscarSeries(seriesData.series, searchQuery.text)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //SearchBar(searchQuery = searchQuery, onQueryChange = { searchQuery = it })
        Spacer(modifier = Modifier.height(16.dp))
        SerieList(series = seriesFiltrada)
    }
}