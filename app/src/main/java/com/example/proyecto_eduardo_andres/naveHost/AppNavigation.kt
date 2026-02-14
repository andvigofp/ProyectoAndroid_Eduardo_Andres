package com.example.proyecto_eduardo_andres.naveHost

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
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.AlquilerPeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository.AlquilerSearchPeliculasRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.AlquilerSerieRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.AlquilerSeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository.AlquilerSearchSeriesRepository
import com.example.proyecto_eduardo_andres.data.repository.camaraRepository.CamaraRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepo
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryHibridoLogin
import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.PeliculasRepositoryHibrido
import com.example.proyecto_eduardo_andres.data.room.AppDatabase
import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.PeliculasRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.PerfilUsuarioRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.qrRepository.QRRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.RecuperarPasswordRepositoryRetrofit
import com.example.proyecto_eduardo_andres.data.repository.seriesRepository.SeriesRepositoryHibrido
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.viewmodel.vm.AppNavigationViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.AppNavigationViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.PerfilSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.PerfilSeriesViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlinePeliculasViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlinePeliculasViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlineSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.VideoClubOnlineSeriesViewModelFactory
import com.example.proyecto_eduardo_andres.vista.pagina.AlquilarDevolverPeliculasScreen
import com.example.proyecto_eduardo_andres.vista.pagina.AlquilerDevolverSeriesScreen
import com.example.proyecto_eduardo_andres.vista.pagina.CamaraScreen
import com.example.proyecto_eduardo_andres.vista.pagina.CrearUsuarioScreen
import com.example.proyecto_eduardo_andres.vista.pagina.LogingScreen
import com.example.proyecto_eduardo_andres.vista.pagina.PerfilSeriesScreen
import com.example.proyecto_eduardo_andres.vista.pagina.PerfilUsuarioScreen
import com.example.proyecto_eduardo_andres.vista.pagina.QRScreen
import com.example.proyecto_eduardo_andres.vista.pagina.RecuperarPasswordScreen
import com.example.proyecto_eduardo_andres.vista.pagina.VideoClubOnlinePeliculasScreen
import com.example.proyecto_eduardo_andres.vista.pagina.VideoClubOnlineSeriesScreen
import com.example.proyecto_eduardo_andres.vista.pagina.VideoClubSearchPeliculasScreen
import com.example.proyecto_eduardo_andres.vista.pagina.VideoClubSearchSeriesScreen
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
    val repositorySearchPeliculas = remember { AlquilerSearchPeliculasRepositoryRetrofit(context) }
    val repositorySearchSeries = remember { AlquilerSearchSeriesRepository(context) }
    val repositoryPerfilUsuario = remember { PerfilUsuarioRepositoryRetrofit() }
    val repositoryPeliculasData = remember {
        PeliculasRepositoryHibrido(
            api = RetrofitClient.peliApiService,
            peliculaDao = AppDatabase.getDatabase(context).peliculaDao(),
            context = context
        )
    }

    val repositorySeriesData = remember {
        SeriesRepositoryHibrido(
            api = RetrofitClient.serieApiService,
            serieDao = AppDatabase.getDatabase(context).serieDao(),
            context = context
        )
    }

    val alquilerRepositoryPelicula = remember {
        AlquilerPeliculasRepositoryInMemory()
    }

    val alquilerRepositorioSerie = remember {
        AlquilerSeriesRepositoryInMemory()
    }



    val application = context.applicationContext as Application

    val userRepository = remember {
        UserRepo(application)
    }

    val appNavigationViewModel: AppNavigationViewModel = viewModel(
        factory = AppNavigationViewModelFactory(
            application = application,
            userRepository = userRepository
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
        factory = LoginViewModelFactory(userRepository)
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
        startDestination = uiState.initialRoute
    ){



    // ---------- LOGIN ----------
        composable<RouteNavigation.Login> {

            LogingScreen(
                onLoginSuccess = {
                    navigate(RouteNavigation.VideoClubPeliculas(it))
                },
                onCrearUsuarioClick = { navigate(RouteNavigation.CrearUsuario) },
                onRecuperarPasswordClick = { navigate(RouteNavigation.RecuperarPassword) },
                loginViewModel = loginViewModel
            )
        }

        // ---------- CREAR USUARIO ----------
        composable<RouteNavigation.CrearUsuario> {
            CrearUsuarioScreen(
                onCrearUsuarioSucess = { navigate(RouteNavigation.Login) },
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
                    navigate(RouteNavigation.AlquilerDevolverPeliculas(route.userId, pelicula.id))
                },
            )
        }

        // ---------- VIDEOCLUB SERIES ----------
        composable<RouteNavigation.VideoClubSeries> { route ->
            val route = route.toRoute<RouteNavigation.VideoClubSeries>()
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
                    navigate(RouteNavigation.AlquilerDevolverSeries(route.userId, serie.id))
                },

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
                onQrClick = { navigate(RouteNavigation.QR(route.userId)) }
            )
        }

        // ---------- PERFIL USUARIO ----------
        composable<RouteNavigation.PerfilUsuario> { route ->
            val route = route.toRoute<RouteNavigation.PerfilUsuario>()
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
                alquilerRepository = alquilerRepositoryPelicula,
                onPeliculaClick = { pelicula ->
                    navigate(RouteNavigation.AlquilerDevolverPeliculas(route.userId, pelicula))
                },
                repository = repositoryPerfilUsuario,
            )
        }

        // ---------- PERFIL SERIES ----------
        composable<RouteNavigation.PerfilSeries> { route ->
            val route = route.toRoute<RouteNavigation.PerfilSeries>()
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
                alquilerRepository = alquilerRepositorioSerie,
                onSerieClieck = { serie ->
                    navigate(RouteNavigation.AlquilerDevolverSeries(route.userId, serie))
                },
                repository = repositoryPerfilUsuario,
            )
        }

        // ---------- ALQUILER / DEVOLVER PELÃCULAS ----------
        composable<RouteNavigation.AlquilerDevolverPeliculas> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<RouteNavigation.AlquilerDevolverPeliculas>()
            AlquilarDevolverPeliculasScreen(
                userId = args.userId,
                repository = alquilerRepositoryPelicula,
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
                repository = alquilerRepositorioSerie,
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