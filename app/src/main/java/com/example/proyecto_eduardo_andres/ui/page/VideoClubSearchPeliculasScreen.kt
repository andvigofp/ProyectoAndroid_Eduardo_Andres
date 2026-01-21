@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.colorAzulOscurso
import com.example.compose.colorAzulSuave
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar
import com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas.MovieItem
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

    // Lista filtrada según búsqueda
    val peliculasFiltradas = remember(uiState.query, uiState.peliculas) {
        uiState.peliculas.filter { pelicula ->
            uiState.query.isBlank() ||
                    context.getString(pelicula.nombrePelicula)
                        .contains(uiState.query, ignoreCase = true)
        }
    }

    // Degradado del toolbar
    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(colorVioleta, colorAzulOscurso),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorAzulSuave)
    ) {
        // ---------- TOOLBAR ----------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(toolbarBackGround)
                .statusBarsPadding()
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                toolBar(
                    onBackClick = onBackClick,
                    onHomeClick = onHomeClick,
                    onCameraClick = onCameraClick,
                    onProfileClick = onProfileClick,
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

            // Lista de películas
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(peliculasFiltradas) { movie ->
                    MovieItem(movie = movie)
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
