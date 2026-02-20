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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.AlquilerPeliculasRepositoryRoom
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository.AlquilerSearchPeliculasRepositoryRoom
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.AlquilerSeriesRepositoryRoom
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesSearchRepository.AlquilerSearchSeriesRepositoryRoom
import com.example.proyecto_eduardo_andres.data.repository.camaraRepository.CamaraRepositoryReal
import com.example.proyecto_eduardo_andres.data.repository.contactoRepository.InfoProyectoRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.crearUsuario.CrearUsuarioRepositoryRoom
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepo
import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.PeliculasRepositoryRoom
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.PerfilUsuarioRepositoryRoom
import com.example.proyecto_eduardo_andres.data.room.AppDatabase
import com.example.proyecto_eduardo_andres.data.repository.qrRepository.QRRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.RecuperarPasswordRepositoryRoom
import com.example.proyecto_eduardo_andres.data.repository.seriesRepository.SeriesRepositoryRoom
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.viewmodel.vm.AppNavigationViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.AppNavigationViewModelFactory
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteLogin.LoginMode
import com.example.proyecto_eduardo_andres.vista.pagina.AlquilarDevolverPeliculasScreen
import com.example.proyecto_eduardo_andres.vista.pagina.AlquilerDevolverSeriesScreen
import com.example.proyecto_eduardo_andres.vista.pagina.CamaraScreen
import com.example.proyecto_eduardo_andres.vista.pagina.CrearUsuarioScreen
import com.example.proyecto_eduardo_andres.vista.pagina.InfoProyectoScreen
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

/**
 *
 *
 * Composable principal encargado de configurar y gestionar
 * toda la navegación de la aplicación.
 *
 * Esta función:
 * - Inicializa NavController.
 * - Crea los repositorios necesarios.
 * - Gestiona la sesión del usuario.
 * - Escucha eventos globales de navegación (SessionEvents).
 * - Define el NavHost con todas las rutas disponibles.
 *
 * Utiliza:
 * - Navigation Compose.
 * - Kotlin Coroutines (Flow).
 * - Arquitectura basada en Repository + ViewModel.
 * - Navegación tipada mediante RouteNavigation.
 *
 * evitando el uso de Strings manuales como rutas.
 *
 * @author Andrés
 *
 * @param Navegacion entre pantallas.
 *
 */
@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val repositoryCamara = remember {
        CamaraRepositoryReal(context)
    }
    val repositoryQR = remember { QRRepositoryInMemory() }

    val repositoryRecuperarPassword = remember {
        RecuperarPasswordRepositoryRoom(
            api = RetrofitClient.recuperarPasswordApiExterna,
        )
    }


    val repositorySearchPeliculas = remember {
        AlquilerSearchPeliculasRepositoryRoom(
            context,
            api = RetrofitClient.alquilerSearchPeliculas,
            searchDao = AppDatabase.getDatabase(context).searchPeliculaDao()
        )
    }


    val repositorySearchSeries = remember {
        AlquilerSearchSeriesRepositoryRoom(
            context,
            api = RetrofitClient.alquilerSearchSeries,
            searchDao = AppDatabase.getDatabase(context).searchSerieDao()
        )
    }

    val repositoryPerfilUsuario = remember {
        PerfilUsuarioRepositoryRoom(
            api = RetrofitClient.perfilUsuario,
        )
    }

    val repositoryPeliculasData = remember {
        PeliculasRepositoryRoom(
            api = RetrofitClient.peliApiService,
            peliculaDao = AppDatabase.getDatabase(context).peliculaDao(),
            context = context
        )
    }

    val repositorySeriesData = remember {
        SeriesRepositoryRoom(
            api = RetrofitClient.serieApiService,
            serieDao = AppDatabase.getDatabase(context).serieDao(),
            context = context
        )
    }

    val alquilerRepositoryPelicula = remember {
        AlquilerPeliculasRepositoryRoom(
            alquilerDao = AppDatabase.getDatabase(context).alquilerPeliculaDao(),
            peliculasRepository = repositoryPeliculasData
        )
    }

    val alquilerRepositorioSerie = remember {
        AlquilerSeriesRepositoryRoom(
            alquilerDao = AppDatabase.getDatabase(context).alquilerSerieDao(),
            seriesRepository = repositorySeriesData
        )
    }

    val repositoryCrearUsuario = remember {
        CrearUsuarioRepositoryRoom(
            authApi = RetrofitClient.authApiService,
        )
    }



    val application = context.applicationContext as Application

    val userRepository = remember {
        UserRepo(
            authApi = RetrofitClient.authApiService,
            usuarioApi = RetrofitClient.usuarioApiService,
            context = context,
        )
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
        factory = LoginViewModelFactory(
            userRepository = userRepository,
        )
    )


    // --- Navegación ---
    fun navigate(route: RouteNavigation) {
        navController.navigate(route)
    }

    val repositoryInfoProyecto = remember {
        InfoProyectoRepositoryInMemory()
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
                is RouteNavigation.InfoProyecto,
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
                loginViewModel = loginViewModel,
            )
        }

        // ---------- CREAR USUARIO ----------
        composable<RouteNavigation.CrearUsuario> {
            CrearUsuarioScreen(
                repository = repositoryCrearUsuario,
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

        // ---------- INFO PROYECTO ----------
        composable<RouteNavigation.InfoProyecto> { route ->

            InfoProyectoScreen(
                repository = repositoryInfoProyecto,
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navigate(RouteNavigation.VideoClubPeliculas(route.id)) },
                onCameraClick = { navigate(RouteNavigation.Camara(route.id)) },
                onProfileClick = { navigate(RouteNavigation.PerfilUsuario(route.id)) },
                onLogoutClick = {
                    loginViewModel.resetState()
                    navigate(RouteNavigation.Login)
                }
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
                onDrawerSeriesClick = { navigate(RouteNavigation.VideoClubSeries(route.userId)) },
                onDrawerInfolick = { navigate(RouteNavigation.InfoProyecto(route.userId)) },
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
                onDrawerInfolick = { navigate(RouteNavigation.InfoProyecto(route.userId)) },
                onDrawerPeliculasClick = { navigate(RouteNavigation.VideoClubPeliculas(route.userId)) },
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