package com.example.proyecto_eduardo_andres.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar
import com.example.proyecto_eduardo_andres.naveHost.AppScreens
import com.example.proyecto_eduardo_andres.viewData.qrData.QRData

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Función helper para los callbacks del toolBar
    fun toolbarCallbacks() = object {
        val onBack: () -> Unit = { navController.popBackStack() }
        val onHome: () -> Unit = { navController.navigate(AppScreens.VideoClubPeliculas.routeId.toString()) }
        val onCamera: () -> Unit = { navController.navigate(AppScreens.Camara.routeId.toString()) }
        val onProfile: () -> Unit = { navController.navigate(AppScreens.PerfilUsuario.routeId.toString()) }
        val onLogout: () -> Unit = { navController.navigate(AppScreens.Login.routeId.toString()) }
    }

    NavHost(
        navController = navController,
        startDestination = AppScreens.Login.routeId.toString()
    ) {

        // LOGIN
        composable(AppScreens.Login.routeId.toString()) {
            LogingScreen(
                userImageUrl = null,
                onAccederClick = { navController.navigate(AppScreens.VideoClubPeliculas.routeId.toString()) },
                onCrearUsuarioClick = { navController.navigate(AppScreens.CrearUsuario.routeId.toString()) },
                onRecuperarPasswordClick = { navController.navigate(AppScreens.RecuperarPassword.routeId.toString()) }
            )
        }

        // CREAR USUARIO
        composable(AppScreens.CrearUsuario.routeId.toString()) {
            CrearUsuarioScreen(
                onCrearUsuarioClick = { navController.navigate(AppScreens.Login.routeId.toString()) },
                onCancelarClick = { navController.popBackStack() }
            )
        }

        // RECUPERAR PASSWORD
        composable(AppScreens.RecuperarPassword.routeId.toString()) {
            RecuperarPasswordScreen(
                onRecuperarClick = {
                    // Lógica de recuperación de contraseña, por ejemplo:
                    navController.navigate(AppScreens.Login.routeId.toString())
                },
                onCancelarClick = { navController.popBackStack() }
            )
        }

        // VIDEOCLUB PELÍCULAS con toolBar
        composable(AppScreens.VideoClubPeliculas.routeId.toString()) {
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

        // VIDEOCLUB SERIES con toolBar
        composable(AppScreens.VideoClubSeries.routeId.toString()) {
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

        // BUSCAR PELÍCULAS
        composable(AppScreens.SearchPeliculas.routeId.toString()) {
            VideoClubSearchPeliculasScreen(navController)
        }

        // BUSCAR SERIES
        composable(AppScreens.SearchSeries.routeId.toString()) {
            VideoClubSearchSeriesScreen(navController)
        }

        // QR con toolBar
        composable(AppScreens.QR.routeId.toString()) {
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
        composable(AppScreens.Camara.routeId.toString()) {
            CamaraScreen()
        }
    }
}
