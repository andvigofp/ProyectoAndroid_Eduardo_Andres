package com.example.proyecto_eduardo_andres.ui.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.repository.alquilerPeliculasRepository.AlquilerPeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.alquilerSeriesRepository.AlquilerSeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.camaraRepository.CamaraRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.crearUsuario.CrearUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.loginRepository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.peliculasRepository.PeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.perfilRepositorio.PerfilUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.qrRepository.QRRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.recuperarPasswordRepository.RecuperarPasswordRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.seriesRepository.SeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.viewData.qrData.QRData
import com.example.proyecto_eduardo_andres.viewmodel.CrearUsuarioViewModel
import com.example.proyecto_eduardo_andres.viewmodel.CrearUsuarioViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.LoginViewModel
import com.example.proyecto_eduardo_andres.viewmodel.LoginViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.PerfilSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.PerfilSeriesViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.PerfilUsuarioViewModel
import com.example.proyecto_eduardo_andres.viewmodel.PerfilUsuarioViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.VideoClubOnlinePeliculasViewModel
import com.example.proyecto_eduardo_andres.viewmodel.VideoClubOnlinePeliculasViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.VideoClubOnlineSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.VideoClubOnlineSeriesViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val repositoryPeliculas = remember { AlquilerPeliculasRepositoryInMemory() }
    val repositorySeries = remember { AlquilerSeriesRepositoryInMemory() }
    val repositoryCamara = remember { CamaraRepositoryInMemory() }
    val repositoryQR = remember { QRRepositoryInMemory() }
    val repositoryRecuperarPassword = remember { RecuperarPasswordRepositoryInMemory() }
    val repositoryPeliculasData = remember { PeliculasRepositoryInMemory() }
    val repositorySeriesData = remember { SeriesRepositoryInMemory() }
    val authRepository = remember {
        UserRepositoryInMemory(RetrofitClient.authApiService)}

    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(authRepository)
    )

    val crearUsuarioViewModel: CrearUsuarioViewModel = viewModel(
        factory = CrearUsuarioViewModelFactory(
            CrearUsuarioRepositoryInMemory(RetrofitClient.authApiService)
        )
    )

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
                is RouteNavigation.PerfilSeries,
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
                loginViewModel = loginViewModel,
                onAccederClick = { navigate(RouteNavigation.VideoClubPeliculas(1)) },
                onCrearUsuarioClick = { navigate(RouteNavigation.CrearUsuario) },
                onRecuperarPasswordClick = { navigate(RouteNavigation.RecuperarPassword) }
            )
        }

        // ---------- CREAR USUARIO ----------
        composable<RouteNavigation.CrearUsuario> {
            CrearUsuarioScreen(
                crearUsuarioViewModel = crearUsuarioViewModel,
                onCrearUsuarioClick = { navigate(RouteNavigation.Login) },
                onCancelarClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- RECUPERAR PASSWORD ----------
        composable<RouteNavigation.RecuperarPassword> {
            RecuperarPasswordScreen(
                repository = repositoryRecuperarPassword,
                onRecuperarClick = { navigate(RouteNavigation.Login) },
                onCancelarClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- VIDEOCLUB PELÍCULAS ----------
        composable<RouteNavigation.VideoClubPeliculas> { route ->
            val route = route.toRoute<RouteNavigation.VideoClubPeliculas>()
            val viewModel: VideoClubOnlinePeliculasViewModel = viewModel(
                factory = VideoClubOnlinePeliculasViewModelFactory(
                    repositoryPeliculasData
                )
            )
            VideoClubOnlinePeliculasScreen(
                repository = repositoryPeliculasData,
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onSearchClick = { navigate(RouteNavigation.SearchPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onDrawerPeliculasClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onDrawerSeriesClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) },
                onPeliculaClick = { pelicula ->
                    viewModel.onPeliculaClick(route.id, pelicula)
                },
                viewModel = viewModel
            )
        }

        // ---------- VIDEOCLUB SERIES ----------
        composable<RouteNavigation.VideoClubSeries> { route ->
            val route = route.toRoute<RouteNavigation.VideoClubSeries>()
            val viewModel: VideoClubOnlineSeriesViewModel = viewModel(
                factory = VideoClubOnlineSeriesViewModelFactory(
                    repositorySeriesData
                )
            )
            VideoClubOnlineSeriesScreen(
                repository = repositorySeriesData,
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onSearchClick = { navigate(RouteNavigation.SearchSeries(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onDrawerPeliculasClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onDrawerSeriesClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) },
                onSerieClick = { serie ->
                    viewModel.onSerieClick(route.id, serie)
                },
                viewModel = viewModel
            )
        }


        // ---------- SEARCH PELÍCULAS ----------
        composable<RouteNavigation.SearchPeliculas> { route ->
            val route = route.toRoute<RouteNavigation.SearchPeliculas>()
            VideoClubSearchPeliculasScreen(
                userId = route.id,
                repository = repositoryPeliculasData,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onPeliculaClick = { nombrePelicula ->
                    navigate(RouteNavigation.AlquilerDevolverPeliculas(route.id, nombrePelicula))
                }
            )
        }

        // ---------- SEARCH SERIES ----------
        composable<RouteNavigation.SearchSeries> { route ->
            val route = route.toRoute<RouteNavigation.SearchSeries>()
            VideoClubSearchSeriesScreen(
                userId = route.id,
                repository = repositorySeriesData,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onSerieClick = { nombreSerie ->
                    navigate(RouteNavigation.AlquilerDevolverSeries(route.id, nombreSerie))
                }
            )
        }

        // ---------- CÁMARA ----------
        composable<RouteNavigation.Camara> { route ->
            val route = route.toRoute<RouteNavigation.Camara>()
            CamaraScreen(
                repository = repositoryCamara,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
            )
        }

        // ---------- PERFIL USUARIO (PELÍCULAS) ----------
        composable<RouteNavigation.PerfilUsuario> { route ->
            val route = route.toRoute<RouteNavigation.PerfilUsuario>()
            val viewModel: PerfilUsuarioViewModel = viewModel(
                factory = PerfilUsuarioViewModelFactory(
                    userId = route.id.toString(),
                    repository = PerfilUsuarioRepositoryInMemory(
                        apiService = RetrofitClient.usuarioApiService
                    ),
                    alquilerRepository = repositoryPeliculas,

                )
            )
            PerfilUsuarioScreen(
                userId = route.id.toString(),
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                viewModel = viewModel
            )
        }

        // ---------- PERFIL SERIES ----------
        composable<RouteNavigation.PerfilSeries> { route ->
            val route = route.toRoute<RouteNavigation.PerfilSeries>()
            val viewModel: PerfilSeriesViewModel = viewModel(
                factory = PerfilSeriesViewModelFactory(
                    userId = route.id.toString(),
                    repository = PerfilUsuarioRepositoryInMemory(
                        apiService = RetrofitClient.usuarioApiService
                    ),
                    alquilerRepository = repositorySeries
                )
            )

            PerfilSeriesScreen(
                userId = route.id.toString(),
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                viewModel = viewModel
            )
        }

        // ---------- ALQUILER / DEVOLVER PELÍCULAS ----------
        composable<RouteNavigation.AlquilerDevolverPeliculas> { route ->
            val route = route.toRoute<RouteNavigation.AlquilerDevolverPeliculas>()
            AlquilarDevolverPeliculasScreen(
                userId = route.userId,
                repository = repositoryPeliculas,
                nombrePelicula = route.nombrePelicula,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) }
            )
        }

        // ---------- ALQUILER / DEVOLVER SERIES ----------
        composable<RouteNavigation.AlquilerDevolverSeries> { route ->
            val route = route.toRoute<RouteNavigation.AlquilerDevolverSeries>()
            AlquilerDevolverSeriesScreen(
                userId = route.userId,
                repository = repositorySeries,
                nombreSerie = route.nombreSerie,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
            )
        }

        // ---------- QR ----------
        composable<RouteNavigation.QR> { route ->
            val route = route.toRoute<RouteNavigation.QR>()
            QRScreen(
                userId = route.id,
                repository = repositoryQR,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
            )
        }
    }
}
