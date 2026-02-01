@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.pagina

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.component1
import kotlin.collections.component2
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.repository.seriesRepository.ISeriesRepository
import com.example.proyecto_eduardo_andres.repository.seriesRepository.SeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlineSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlineSeriesViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteMenu.VideoClubMenuDrawer
import com.example.proyecto_eduardo_andres.vista.componente.componenteToolbar.toolBarVideoClubOnline
import com.example.proyecto_eduardo_andres.vista.componente.componenteVideoClubListaPeliculas.VideoClubCategoriasBotones
import com.example.proyecto_eduardo_andres.vista.componente.componenteVideoClubOnlieSeries.SerieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubOnlineSeriesScreen(
    repository: ISeriesRepository,
    viewModel: VideoClubOnlineSeriesViewModel = viewModel(
        factory = VideoClubOnlineSeriesViewModelFactory(repository)
    ),
    onHomeClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCameraClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onDrawerPeliculasClick: () -> Unit = {},
    onDrawerSeriesClick: () -> Unit = {},
    onSerieClick: (VideoClubOnlineSeriesData) -> Unit,
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
                onPeliculasClick = onDrawerPeliculasClick,
                onSeriesClick = onDrawerSeriesClick
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
                            colors = listOf(colorVioleta, colorAzulOscurso)
                        )
                    )
            ) {
                Column(modifier = Modifier.statusBarsPadding()) {
                    val scope = rememberCoroutineScope()

                    toolBarVideoClubOnline(
                        drawerState = drawerState,
                        scope = scope,
                        onHomeClick = onHomeClick,
                        onSearchClick = onSearchClick,
                        onCameraClick = onCameraClick,
                        onProfileClick = onProfileClick,
                        onLogoutClick = onLogoutClick
                    )
                }
            }

            // ---------- CONTENIDO ----------
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = toolbarHeight)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item { Spacer(modifier = Modifier.height(2.dp)) }

                // Botones de categorías con callback
                item {
                    VideoClubCategoriasBotones(
                        onCategoriaClick = { categoriaId -> viewModel.filtrarPorCategoria(categoriaId) },
                        modifier = Modifier.statusBarsPadding()
                    )
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }

                // Series agrupadas por categoría desde el ViewModel
                uiState.seriesPorCategoria.forEach { (categoria, series) ->
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
                                items(series) { serie ->
                                    SerieItem(
                                        serie = serie,
                                        onClick = {
                                            onSerieClick(serie)
                                        }
                                    )
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
fun VideoClubOnlineSeriesScreenPreview() {
    val repository = SeriesRepositoryInMemory()
    val viewModel: VideoClubOnlineSeriesViewModel = viewModel(
        factory = VideoClubOnlineSeriesViewModelFactory(repository)
    )

    MaterialTheme {
        VideoClubOnlineSeriesScreen(
            repository = repository,
            viewModel = viewModel,
            onSerieClick = {} // <- obligatorio para navegar
        )
    }
}
