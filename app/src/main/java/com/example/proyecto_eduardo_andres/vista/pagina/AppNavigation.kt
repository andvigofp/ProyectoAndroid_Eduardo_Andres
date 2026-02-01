package com.example.proyecto_eduardo_andres.vista.pagina

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.AlquilerPeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.AlquilerSeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.camaraRepository.CamaraRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.crearUsuario.CrearUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.PeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.PerfilUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.qrRepository.QRRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.RecuperarPasswordRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.seriesRepository.SeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.viewmodel.vm.CrearUsuarioViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.CrearUsuarioViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.PerfilSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.PerfilSeriesViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.PerfilUsuarioViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.PerfilUsuarioViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlinePeliculasViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlinePeliculasViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlineSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlineSeriesViewModelFactory
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
        UserRepositoryInMemory(RetrofitClient.authApiService)
    }

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
                onAccederClick = {
                    loginViewModel.logging {
                        val userId = loginViewModel.loggedInUserId ?: return@logging
                        navigate(RouteNavigation.VideoClubPeliculas(userId))
                    }
                },
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
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onSearchClick = { navigate(RouteNavigation.SearchPeliculas(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onDrawerPeliculasClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onDrawerSeriesClick = { navigate(RouteNavigation.VideoClubSeries(route.userId)) },
                onPeliculaClick = { pelicula ->
                    viewModel.onPeliculaClick(route.userId, pelicula)
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
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onSearchClick = { navigate(RouteNavigation.SearchSeries(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onDrawerPeliculasClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onDrawerSeriesClick = { navigate(RouteNavigation.VideoClubSeries(route.userId)) },
                onSerieClick = { serie ->
                    viewModel.onSerieClick(route.userId, serie)
                },
                viewModel = viewModel
            )
        }


        // ---------- SEARCH PELÍCULAS ----------
        composable<RouteNavigation.SearchPeliculas> { route ->
            val route = route.toRoute<RouteNavigation.SearchPeliculas>()
            VideoClubSearchPeliculasScreen(
                userId = route.userId,
                repository = repositoryPeliculasData,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onPeliculaClick = { nombrePelicula ->
                    navigate(RouteNavigation.AlquilerDevolverPeliculas(route.userId, nombrePelicula))
                }
            )
        }

        // ---------- SEARCH SERIES ----------
        composable<RouteNavigation.SearchSeries> { route ->
            val route = route.toRoute<RouteNavigation.SearchSeries>()
            VideoClubSearchSeriesScreen(
                userId = route.userId,
                repository = repositorySeriesData,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                onSerieClick = { nombreSerie ->
                    navigate(RouteNavigation.AlquilerDevolverSeries(route.userId, nombreSerie))
                }
            )
        }

        // ---------- CÁMARA ----------
        composable<RouteNavigation.Camara> { route ->
            val route = route.toRoute<RouteNavigation.Camara>()
            CamaraScreen(
                repository = repositoryCamara,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
            )
        }

        // ---------- PERFIL USUARIO (PELÍCULAS) ----------
        composable<RouteNavigation.PerfilUsuario> { route ->
            val route = route.toRoute<RouteNavigation.PerfilUsuario>()
            val viewModel: PerfilUsuarioViewModel = viewModel(
                factory = PerfilUsuarioViewModelFactory(
                    userId = route.userId,
                    repository = PerfilUsuarioRepositoryInMemory(
                        apiService = RetrofitClient.usuarioApiService
                    ),
                    alquilerRepository = repositoryPeliculas,

                )
            )
            PerfilUsuarioScreen(
                userId = route.userId,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
                viewModel = viewModel
            )
        }

        // ---------- PERFIL SERIES ----------
        composable<RouteNavigation.PerfilSeries> { route ->
            val route = route.toRoute<RouteNavigation.PerfilSeries>()
            val viewModel: PerfilSeriesViewModel = viewModel(
                factory = PerfilSeriesViewModelFactory(
                    userId = route.userId,
                    repository = PerfilUsuarioRepositoryInMemory(
                        apiService = RetrofitClient.usuarioApiService
                    ),
                    alquilerRepository = repositorySeries
                )
            )

            PerfilSeriesScreen(
                userId = route.userId,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(route.userId)) },
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
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId.toString())) },
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
                userId = route.userId,
                repository = repositoryQR,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId)) },
                onLogoutClick = { navigate(RouteNavigation.Login) },
            )
        }
    }
}