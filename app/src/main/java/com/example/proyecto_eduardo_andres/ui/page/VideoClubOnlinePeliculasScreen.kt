@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componenteMenu.VideoClubMenuDrawer
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBarVideoClubOnline
import com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas.VideoClubCategoriasBotones
import com.example.proyecto_eduardo_andres.naveHost.AppScreens
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.PeliculaItem
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.SeriesData
import com.example.proyecto_eduardo_andres.viewmodel.VideoClubOnlinePeliculasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubOnlinePeliculasScreen(
    navController: NavController,
    viewModel: VideoClubOnlinePeliculasViewModel = viewModel()// O viewModel() si no usas Hilt
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val toolbarHeight = 56.dp + WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    // Observar estado UI del ViewModel
    val uiState by viewModel.uiState.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            VideoClubMenuDrawer(
                drawerState = drawerState,
                scope = scope,
                onPeliculasClick = {},
                onSeriesClick = {}
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // ---------- TOOLBAR ----------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(toolbarHeight)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                colorVioleta, colorAzulOscurso
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier.statusBarsPadding() // para no tapar status bar
                ) {
                    toolBarVideoClubOnline(
                        drawerState = drawerState,
                        scope = scope,
                        onHomeClick = { navController.navigate(AppScreens.VideoClubPeliculas.routeId.toString()) },
                        onSearchClick = { navController.navigate(AppScreens.SearchSeries.routeId.toString()) },
                        onCameraClick = { navController.navigate(AppScreens.Camara.routeId.toString()) },
                        onProfileClick = { navController.navigate(AppScreens.PerfilUsuario.routeId.toString()) },
                        onLogoutClick = { navController.navigate(AppScreens.Login.routeId.toString()) }
                    )
                }
            }

            // ---------- CONTENIDO ----------
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = toolbarHeight) // debajo toolbar
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item { Spacer(modifier = Modifier.height(2.dp)) }

                // Botones de categorías, ahora con callback para filtrar
                item {
                    VideoClubCategoriasBotones(
                        onCategoriaClick = { categoriaId ->
                            viewModel.filtrarPorCategoria(categoriaId)
                        },
                        modifier = Modifier.statusBarsPadding() // separa del status bar si quieres
                    )
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }

                // Mostrar películas desde el ViewModel, que ya están filtradas/agrupadas
                uiState.peliculasPorCategoria.forEach { (categoria, peliculas) ->
                    item(key = categoria) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(categoria),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(peliculas) { pelicula ->
                                    PeliculaItem(pelicula)
                                }
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubOnlineScreenPreview() {
    val navController = rememberNavController()
    val viewModel: VideoClubOnlinePeliculasViewModel = viewModel() // tu ViewModel real

    MaterialTheme {
        VideoClubOnlinePeliculasScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}
