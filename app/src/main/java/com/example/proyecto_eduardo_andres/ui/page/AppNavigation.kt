package com.example.proyecto_eduardo_andres.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar
import com.example.proyecto_eduardo_andres.naveHost.Screen
import com.example.proyecto_eduardo_andres.viewData.qrData.QRData

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // Callbacks del toolbar
    fun toolbarCallbacks() = object {
        val onBack: () -> Unit = { navController.popBackStack() }
        val onHome: () -> Unit = {
            navController.navigate(Screen.VideoClubPeliculas.routeId.toString())
        }
        val onCamera: () -> Unit = {
            navController.navigate(Screen.Camara.routeId.toString())
        }
        val onProfile: () -> Unit = {
            navController.navigate(Screen.PerfilUsuario.routeId.toString())
        }
        val onLogout: () -> Unit = {
            navController.navigate(Screen.Login.routeId.toString())
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Login.routeId.toString()
    ) {

        // LOGIN
        composable(Screen.Login.routeId.toString()) {
            LogingScreen(
                userImageUrl = null,
                onAccederClick = {
                    navController.navigate(
                        Screen.VideoClubPeliculas.routeId.toString()
                    )
                },
                onCrearUsuarioClick = {
                    navController.navigate(
                        Screen.CrearUsuario.routeId.toString()
                    )
                },
                onRecuperarPasswordClick = {
                    navController.navigate(
                        Screen.RecuperarPassword.routeId.toString()
                    )
                }
            )
        }

        // CREAR USUARIO
        composable(Screen.CrearUsuario.routeId.toString()) {
            CrearUsuarioScreen(
                onCrearUsuarioClick = {
                    navController.navigate(Screen.Login.routeId.toString())
                },
                onCancelarClick = { navController.popBackStack() }
            )
        }

        // RECUPERAR PASSWORD
        composable(Screen.RecuperarPassword.routeId.toString()) {
            RecuperarPasswordScreen(
                onRecuperarClick = {
                    navController.navigate(Screen.Login.routeId.toString())
                },
                onCancelarClick = { navController.popBackStack() }
            )
        }

        // VIDEOCLUB PELÍCULAS
        composable(Screen.VideoClubPeliculas.routeId.toString()) {
            val callbacks = toolbarCallbacks()
            Column {
                toolBar(
                    onBackClick = callbacks.onBack,
                    onHomeClick = callbacks.onHome,
                    onCameraClick = callbacks.onCamera,
                    onProfileClick = callbacks.onProfile,
                    onLogoutClick = callbacks.onLogout
                )
                VideoClubOnlinePeliculasScreen(navController)
            }
        }

        // VIDEOCLUB SERIES
        composable(Screen.VideoClubSeries.routeId.toString()) {
            val callbacks = toolbarCallbacks()
            Column {
                toolBar(
                    onBackClick = callbacks.onBack,
                    onHomeClick = callbacks.onHome,
                    onCameraClick = callbacks.onCamera,
                    onProfileClick = callbacks.onProfile,
                    onLogoutClick = callbacks.onLogout
                )
                VideoClubOnlineSeriesScreen(navController)
            }
        }

        //ALQUILER / DEVOLVER SERIES
        composable(Screen.AlquilerDevolverSeries.routeId.toString()) {
            val callbacks = toolbarCallbacks()
            Column {
                toolBar(
                    onBackClick = callbacks.onBack,
                    onHomeClick = callbacks.onHome,
                    onCameraClick = callbacks.onCamera,
                    onProfileClick = callbacks.onProfile,
                    onLogoutClick = callbacks.onLogout
                )
                AlquilerDevolverSeriesScreen(navController)
            }
        }

        //ALQUILER / DEVOLVER PELÍCULAS
        composable(Screen.AlquilerDevolverPeliculas.routeId.toString()) {
            val callbacks = toolbarCallbacks()
            Column {
                toolBar(
                    onBackClick = callbacks.onBack,
                    onHomeClick = callbacks.onHome,
                    onCameraClick = callbacks.onCamera,
                    onProfileClick = callbacks.onProfile,
                    onLogoutClick = callbacks.onLogout
                )
                AlquilarDevolverPeliculasScreen(navController)
            }
        }

        // BUSCAR PELÍCULAS
        composable(Screen.SearchPeliculas.routeId.toString()) {
            VideoClubSearchPeliculasScreen(navController)
        }

        // BUSCAR SERIES
        composable(Screen.SearchSeries.routeId.toString()) {
            VideoClubSearchSeriesScreen(navController)
        }

        // QR
        composable(Screen.QR.routeId.toString()) {
            val callbacks = toolbarCallbacks()
            Column {
                toolBar(
                    onBackClick = callbacks.onBack,
                    onHomeClick = callbacks.onHome,
                    onCameraClick = callbacks.onCamera,
                    onProfileClick = callbacks.onProfile,
                    onLogoutClick = callbacks.onLogout
                )
                QRScreen(
                    qrData = QRData(""),
                    onBackClick = callbacks.onBack,
                    onHomeClick = callbacks.onHome,
                    onCameraClick = callbacks.onCamera,
                    onProfileClick = callbacks.onProfile,
                    onLogoutClick = callbacks.onLogout
                )
            }
        }

        // CÁMARA
        composable(Screen.Camara.routeId.toString()) {
            CamaraScreen()
        }
    }
}
