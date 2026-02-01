@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteAlquilarDevolverPeliculas

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewmodel.ustate.AlquilarDevolverPeliculasUiState
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData

/**
 * @author Eduardo
 * @see Componenente imagen descripción
 * @param peliculas: Listado de peliculas a mostrar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlquilerDevolverPeliculas(
    peliculas: AlquilarDevolverPeliculasUiState
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Imagen
            if (peliculas.imagen != null) {
                Image(
                    painter = painterResource(id = peliculas.imagen!!),
                    contentDescription = stringResource(peliculas.nombrePelicula),
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, colors.outline, RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Movie,
                    contentDescription = stringResource(R.string.icono_película),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp),
                    tint = colors.onBackground
                )
            }

            // Nombre y descripción
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(peliculas.nombrePelicula),
                    style = typography.headlineMedium.copy(color = colors.primary),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(peliculas.descripcion),
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
    val peliculaDemo = AlquilarDevolverPeliculasUiState(
        pelicula = VideoClubOnlinePeliculasData(
            id = 1,
            categoria = R.string.drama,
            imagen = R.drawable.ic_cadena_perpetua,
            nombre = R.string.cadena_perpetua,
            descripcion = R.string.banquero_inocente
        ),
        peliculaAlquilada  = false,
        fechaAlquiler = null,
        fechaDevolucion = null
    )


    // Renderizar el componente con el estado definido
    MaterialTheme {
        AlquilerDevolverPeliculas(peliculas = peliculaDemo)
    }
}
