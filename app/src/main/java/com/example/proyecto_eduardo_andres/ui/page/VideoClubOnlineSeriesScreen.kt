@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.ui.page

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
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
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
import com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas.VideoClubCategoriasBotones
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.SeriesData
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBarVideoClubOnline
import kotlin.collections.component1
import kotlin.collections.component2
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.naveHost.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoClubOnlineSeriesScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
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
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    colorVioleta, colorAzulOscurso
                                )
                            )
                        )
                        .statusBarsPadding()
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
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item { Spacer(modifier = Modifier.height(12.dp)) }
                item { VideoClubCategoriasBotones() }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                val seriesData = SeriesData()
                val categoriasAgrupadas = seriesData.nombreSeries.groupBy { it.nombreCategoria }
                categoriasAgrupadas.forEach { (categoria, series) ->
                    item(key = categoria) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource( categoria),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(series.size) { index ->
                                    val serie = series[index]
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(6.dp),
                                        modifier = Modifier.width(130.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(130.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(MaterialTheme.colorScheme.inversePrimary)
                                                .clickable { /* onSerieClick(serie) */ },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            serie.imagen?.let {
                                                Image(
                                                    painter = painterResource(id = it),
                                                    contentDescription = stringResource(serie.nombreSerie),
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
                                            text = stringResource(serie.nombreSerie),
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
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubOnlineSeriesScreenPreview() {
    MaterialTheme {
        val navController = rememberNavController()
        VideoClubOnlineSeriesScreen(navController)
    }
}