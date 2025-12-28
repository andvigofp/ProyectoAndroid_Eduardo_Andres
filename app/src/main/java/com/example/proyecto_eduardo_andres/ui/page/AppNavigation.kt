package com.example.proyecto_eduardo_andres.ui.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.repository.AlquilerPeliculasRepository.AlquilerPeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.AlquilerSeriesRepository.AlquilerSeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.viewData.qrData.QRData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val repositoryPeliculas = remember { AlquilerPeliculasRepositoryInMemory() }
    val repositorySeries = remember { AlquilerSeriesRepositoryInMemory() }

    // Helper para emitir navegación
    fun navigate(route: RouteNavigation) {
        scope.launch { SessionEvents.emitNavigation(route) }
    }

    // FORBIDDEN → LOGIN
    LaunchedEffect(Unit) {
        SessionEvents.forbidden.collect {
            navigate(RouteNavigation.Login)
        }
    }

    // NAVEGACIÓN GLOBAL
    LaunchedEffect(Unit) {
        SessionEvents.navigation.collectLatest { route ->
            when (route) {
                is RouteNavigation.Login,
                is RouteNavigation.CrearUsuario,
                is RouteNavigation.RecuperarPassword,
                is RouteNavigation.VideoClubPeliculas,
                is RouteNavigation.VideoClubSeries,
                is RouteNavigation.SearchPeliculas,
                is RouteNavigation.SearchSeries,
                is RouteNavigation.Camara,
                is RouteNavigation.PerfilUsuario,
                is RouteNavigation.AlquilerDevolverPeliculas,
                is RouteNavigation.AlquilerDevolverSeries,
                is RouteNavigation.QR -> navController.navigate(route)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = RouteNavigation.Login
    ) {

        // ---------- LOGIN ----------
        composable<RouteNavigation.Login> {
            LogingScreen(
                userImageUrl = null,
                onAccederClick = { navigate(RouteNavigation.VideoClubPeliculas(1)) },
                onCrearUsuarioClick = { navigate(RouteNavigation.CrearUsuario) },
                onRecuperarPasswordClick = { navigate(RouteNavigation.RecuperarPassword) }
            )
        }

        // ---------- CREAR USUARIO ----------
        composable<RouteNavigation.CrearUsuario> {
            CrearUsuarioScreen(
                onCrearUsuarioClick = { navigate(RouteNavigation.Login) },
                onCancelarClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- RECUPERAR PASSWORD ----------
        composable<RouteNavigation.RecuperarPassword> {
            RecuperarPasswordScreen(
                onRecuperarClick = { navigate(RouteNavigation.Login) },
                onCancelarClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- VIDEOCLUB PELÍCULAS ----------
        composable<RouteNavigation.VideoClubPeliculas> { route ->
            val route = route.toRoute<RouteNavigation.VideoClubPeliculas>()
            VideoClubOnlinePeliculasScreen(
                //userId = route.id,
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onSearchClick = { navigate(RouteNavigation.SearchPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onDrawerPeliculasClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onDrawerSeriesClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) }
            )
        }

        // ---------- VIDEOCLUB SERIES ----------
        composable<RouteNavigation.VideoClubSeries> { route ->
            val route = route.toRoute<RouteNavigation.VideoClubSeries>()
            VideoClubOnlineSeriesScreen(
                //userId = route.id,
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onSearchClick = { navigate(RouteNavigation.SearchSeries(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- SEARCH PELÍCULAS ----------
        composable<RouteNavigation.SearchPeliculas> { route ->
            val route = route.toRoute<RouteNavigation.SearchPeliculas>()
            VideoClubSearchPeliculasScreen(
                //userId = route.id,
                onBackClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- SEARCH SERIES ----------
        composable<RouteNavigation.SearchSeries> { route ->
            val route = route.toRoute<RouteNavigation.SearchSeries>()
            VideoClubSearchSeriesScreen(
                //userId = route.id,
                onBackClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- CÁMARA ----------
        composable<RouteNavigation.Camara> { route ->
            val route = route.toRoute<RouteNavigation.Camara>()
            CamaraScreen(
                //userId = route.id,
                onBackClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- PERFIL ----------
        composable<RouteNavigation.PerfilUsuario> { route ->
            val route = route.toRoute<RouteNavigation.PerfilUsuario>()
            PerfilUsuarioScreen(
                //userId = route.id,
                onBackClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- ALQUILER / DEVOLVER PELÍCULAS ----------
        composable<RouteNavigation.AlquilerDevolverPeliculas> { route ->
            val route = route.toRoute<RouteNavigation.AlquilerDevolverPeliculas>()
            AlquilarDevolverPeliculasScreen(
                userId = route.id,
                repository = repositoryPeliculas,
                onBackClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- ALQUILER / DEVOLVER SERIES ----------
        composable<RouteNavigation.AlquilerDevolverSeries> { route ->
            val route = route.toRoute<RouteNavigation.AlquilerDevolverSeries>()
            AlquilerDevolverSeriesScreen(
                userId = route.id,
                repository = repositorySeries,
                onBackClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
            )
        }

        // ---------- QR ----------
        composable<RouteNavigation.QR> { route ->
            val route = route.toRoute<RouteNavigation.QR>()
            QRScreen(
                //userId = route.id,
                onBackClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                qrData = QRData("")
            )
        }
    }
}
