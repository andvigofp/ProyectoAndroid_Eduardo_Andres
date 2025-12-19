package com.example.proyecto_eduardo_andres.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.viewData.qrData.QRData
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    // Escuchar eventos de navegación
    LaunchedEffect(Unit) {
        SessionEvents.navigation.collect { route ->
            when (route) {
                is RouteNavigation.Login -> {
                    navController.navigate(RouteNavigation.Login) {
                        popUpTo(0)
                    }
                }
                else -> navController.navigate(route)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = RouteNavigation.Login
    ) {
        // LOGIN
        composable<RouteNavigation.Login> {
            LogingScreen(
                userImageUrl = null,
                onAccederClick = { /* normal ViewModel action */ },
                onCrearUsuarioClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.CrearUsuario) } },
                onRecuperarPasswordClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.RecuperarPassword) } }
            )
        }

        // CREAR USUARIO
        composable<RouteNavigation.CrearUsuario> {
            CrearUsuarioScreen(
                onCrearUsuarioClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } },
                onCancelarClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } }
            )
        }

        // RECUPERAR PASSWORD
        composable<RouteNavigation.RecuperarPassword> {
            RecuperarPasswordScreen(
                onRecuperarClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } },
                onCancelarClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } }
            )
        }

        // VIDEOCLUB PELÍCULAS
        composable<RouteNavigation.VideoClubPeliculas> {
                VideoClubOnlinePeliculasScreen(
                    onHomeClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubPeliculas) } },
                    onSearchClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.SearchPeliculas) } },
                    onCameraClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Camara) } },
                    onProfileClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.PerfilUsuario) } },
                    onLogoutClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } },
                    onDrawerPeliculasClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubPeliculas) } },
                    onDrawerSeriesClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } }
                )
            }

        // VIDEOCLUB SERIES
        composable<RouteNavigation.VideoClubSeries> {
                VideoClubOnlineSeriesScreen(
                    onHomeClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubPeliculas) } },
                    onSearchClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.SearchSeries) } },
                    onCameraClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Camara) } },
                    onProfileClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.PerfilUsuario) } },
                    onLogoutClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } },
                    onDrawerPeliculasClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubPeliculas) } },
                    onDrawerSeriesClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } }
                )
            }


        // ALQUILER / DEVOLVER PELÍCULAS
        composable<RouteNavigation.AlquilerDevolverPeliculas> {
            Column {
                AlquilarDevolverPeliculasScreen(
                    onBackClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubPeliculas) } },
                    onHomeClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubPeliculas) } },
                    onCameraClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Camara) } },
                    onProfileClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.PerfilUsuario) } },
                    onLogoutClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } }
                )
            }
        }

        // ALQUILER / DEVOLVER SERIES
        composable<RouteNavigation.AlquilerDevolverSeries> {
            Column {
                AlquilerDevolverSeriesScreen(
                    onBackClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } },
                    onHomeClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } },
                    onCameraClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Camara) } },
                    onProfileClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.PerfilUsuario) } },
                    onLogoutClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } }
                )
            }
        }

        // BUSCAR PELÍCULAS
        composable<RouteNavigation.SearchPeliculas> {
            VideoClubSearchPeliculasScreen(
                onBackClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } },
                onHomeClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } },
                onCameraClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Camara) } },
                onProfileClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.PerfilUsuario) } },
                onLogoutClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } }
            )
        }

        // BUSCAR SERIES
        composable<RouteNavigation.SearchSeries> {
            VideoClubSearchSeriesScreen(
                onBackClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } },
                onHomeClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } },
                onCameraClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Camara) } },
                onProfileClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.PerfilUsuario) } },
                onLogoutClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } }
            )
        }

        // QR
        composable<RouteNavigation.QR> {
            QRScreen(
                qrData = QRData(""),
                onBackClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } },
                onHomeClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubSeries) } },
                onCameraClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Camara) } },
                onProfileClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.PerfilUsuario) } },
                onLogoutClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } }
            )
        }

        // CÁMARA
        composable<RouteNavigation.Camara> {
            CamaraScreen(
                onBackClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubPeliculas) } },
                onHomeClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.VideoClubPeliculas) } },
                onCameraClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Camara) } },
                onProfileClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.PerfilUsuario) } },
                onLogoutClick = { scope.launch { SessionEvents.emitNavigation(RouteNavigation.Login) } }
            )
        }
    }
}
