package com.example.proyecto_eduardo_andres.vista.pagina

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.proyecto_eduardo_andres.data.repository.seriesRepository.SeriesRepositoryRetrofit
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.AlquilerPeliculaRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository.AlquilerSearchPeliculasRepository
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.AlquilerSerieRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository.AlquilerSearchSeriesRepository
import com.example.proyecto_eduardo_andres.data.repository.camaraRepository.CamaraRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.crearUsuario.CrearUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.PeliculasRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.PerfilUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.PerfilUsuarioRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.qrRepository.QRRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.RecuperarPasswordRepositoryRetrofit
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.viewmodel.vm.AppNavigationViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.AppNavigationViewModelFactory
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

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val repositoryPeliculas = remember { AlquilerPeliculaRepositoryRetrofit(context) }
    val repositorySeries = remember { AlquilerSerieRepositoryRetrofit(context) }
    val repositoryCamara = remember { CamaraRepositoryInMemory() }
    val repositoryQR = remember { QRRepositoryInMemory() }
    val repositoryRecuperarPassword = remember { RecuperarPasswordRepositoryRetrofit() }
    val repositoryPeliculasData = remember { PeliculasRepositoryRetrofit(context) }
    val repositorySeriesData = remember { SeriesRepositoryRetrofit(context) }
    val repositorySearchPeliculas = remember { AlquilerSearchPeliculasRepository(context) }
    val repositorySearchSeries = remember { AlquilerSearchSeriesRepository(context) }
    val repositoryPerfilUsuario = remember { PerfilUsuarioRepositoryRetrofit() }

    val authRepository = remember {
        UserRepositoryInMemory(RetrofitClient.authApiService)
    }

    // --- NUEVO: AppNavigationViewModel ---
    val appNavigationViewModel: AppNavigationViewModel = viewModel(
        factory = AppNavigationViewModelFactory(
            application = context.applicationContext as Application,
            userRepository = authRepository
        )
    )

    val uiState by appNavigationViewModel.uiState.collectAsState()

    // --- Mostrar loader mientras se chequea la sesión ---
    if (uiState.isCheckingSession) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(authRepository)
    )

    val crearUsuarioViewModel: CrearUsuarioViewModel = viewModel(
        factory = CrearUsuarioViewModelFactory(
            CrearUsuarioRepositoryInMemory(RetrofitClient.authApiService)
        )
    )

    // --- Navegación ---
    fun navigate(route: RouteNavigation) {
        navController.navigate(route)
    }

    // Página LOGIN
    LaunchedEffect(Unit) {
        SessionEvents.forbidden.collect {
            navigate(RouteNavigation.Login)
        }
    }

    // Navegación global GLOBAL
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

        // ---------- VIDEOCLUB PELICULAS ----------
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
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
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
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
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
            // Aquí pasamos el repositorySearchPeliculas al Screen de búsqueda de películas
            VideoClubSearchPeliculasScreen(
                userId = route.userId,
                repository = repositorySearchPeliculas, // Pasamos el nuevo repository
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId)) },
                onLogoutClick = {
                    // Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
                onPeliculaClick = { pelicula ->
                    navigate(RouteNavigation.AlquilerDevolverPeliculas(route.userId, pelicula.id))
                }
            )
        }


        // ---------- SEARCH SERIES ----------
        composable<RouteNavigation.SearchSeries> { route ->
            val route = route.toRoute<RouteNavigation.SearchSeries>()
            VideoClubSearchSeriesScreen(
                userId = route.userId,
                repository = repositorySearchSeries,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(route.userId)) },
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
                onSerieClick = { serie ->
                    navigate(RouteNavigation.AlquilerDevolverSeries(route.userId, serie.id))
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
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
            )
        }

        // ---------- PERFIL USUARIO ----------
        composable<RouteNavigation.PerfilUsuario> { route ->
            val route = route.toRoute<RouteNavigation.PerfilUsuario>()
            val viewModel: PerfilUsuarioViewModel = viewModel(
                factory = PerfilUsuarioViewModelFactory(
                    userId = route.userId,
                    repository = repositoryPerfilUsuario,
                    alquilerRepository = repositoryPeliculas
                )
            )
            PerfilUsuarioScreen(
                userId = route.userId,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.userId)) },
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
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
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
                viewModel = viewModel
            )
        }

        // ---------- ALQUILER / DEVOLVER PELÃCULAS ----------
        composable<RouteNavigation.AlquilerDevolverPeliculas> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<RouteNavigation.AlquilerDevolverPeliculas>()
            AlquilarDevolverPeliculasScreen(
                userId = args.userId,
                repository = repositoryPeliculas,
                peliculaId = args.peliculaId,  // <-- CORREGIDO: "peliculaId" con 'u'
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(args.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(args.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(args.userId)) }, // <-- Ya es String, no .toString()
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
            )
        }

        // ---------- ALQUILER / DEVOLVER SERIES ----------
        composable<RouteNavigation.AlquilerDevolverSeries> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<RouteNavigation.AlquilerDevolverSeries>()
            AlquilerDevolverSeriesScreen(
                userId = args.userId,
                repository = repositorySeries,
                serieId = args.serieId,  // <-- AsegÃºrate que coincida con RouteNavigation
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubSeries(args.userId)) },
                onCameraClick = { navigate(RouteNavigation.Camara(args.userId)) },
                onProfileClick = { navigate(RouteNavigation.PerfilSeries(args.userId)) },
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
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
                onLogoutClick = {
                    // IMPORTANTE: Resetear estado del login antes de navegar
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                },
            )
        }
    }
}