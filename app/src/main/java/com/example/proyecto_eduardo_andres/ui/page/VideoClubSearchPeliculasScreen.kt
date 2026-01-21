@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.ui.page

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.coloAuzlClaro
import com.example.compose.colorAzulOscurso
import com.example.compose.colorAzulSuave
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas.MovieList
import com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries.SearchBar
import com.example.proyecto_eduardo_andres.viewmodel.VideoClubOnlineSearchPeliculasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubSearchPeliculasScreen(
    viewModel: VideoClubOnlineSearchPeliculasViewModel = viewModel(),
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    val peliculasFiltradas = remember(uiState.query, uiState.peliculas) {
        uiState.peliculas.filter { pelicula ->
            uiState.query.isBlank() ||
                    context.getString(pelicula.nombrePelicula)
                        .contains(uiState.query, ignoreCase = true)
        }
    }

    // Degradado del toolbar
    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(
            colorVioleta,
            colorAzulOscurso
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorAzulSuave) // fondo general
    ) {
        // ---------- TOOLBAR ----------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(toolbarBackGround)
                .statusBarsPadding() // espacio para la barra de estado
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp)) // espacio para reloj
                toolBar(
                    onBackClick = onBackClick,
                    onHomeClick = onHomeClick,
                    onCameraClick = onCameraClick,
                    onProfileClick =onProfileClick,
                    onLogoutClick = onLogoutClick
                )
            }
        }

        // ---------- CONTENIDO ----------
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Barra de búsqueda
            SearchBar(
                searchQuery = uiState.query,
                onQueryChange = { viewModel.onQueryChange(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            MovieList(peliculas = peliculasFiltradas)

            // Lista de películas
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(peliculasFiltradas) { movie ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(coloAuzlClaro, RoundedCornerShape(12.dp))
                            .padding(8.dp)
                    ) {
                        if (movie.imagen != null) {
                            Image(
                                painter = painterResource(id = movie.imagen),
                                contentDescription = stringResource(movie.nombrePelicula),
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
                                Text(
                                    stringResource(R.string.img),
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = stringResource(movie.nombrePelicula),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                            Text(
                                text = stringResource(movie.nombreCategoria),
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
fun VideoClubSearchScreenPeliculasPreview() {
    VideoClubSearchPeliculasScreen(
        onBackClick = {},
        onHomeClick = {},
        onCameraClick = {},
        onProfileClick = {},
        onLogoutClick = {}
    )
}
