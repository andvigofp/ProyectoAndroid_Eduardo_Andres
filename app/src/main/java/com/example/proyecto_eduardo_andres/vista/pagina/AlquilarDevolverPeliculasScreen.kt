@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.pagina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.AlquilerPeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.viewmodel.vm.AlquilarDevolverPeliculasViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.AlquilarDevolverPeliculasViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlquilarDevolverPeliculas.AlquilerDevolverPeliculas
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlquilarDevolverPeliculas.BotonAlquilarPeliculas
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlquilerDevolverPeliculasSeriesDialog.AlquilarDevolverDialog
import com.example.proyecto_eduardo_andres.vista.componente.componenteCustomScreenPeliculasSeries.CustomScreenWithoutScaffold
import com.example.proyecto_eduardo_andres.vista.componente.componenteToolbar.toolBar

@Composable
fun AlquilarDevolverPeliculasScreen(
    userId: String,
    peliculaId: String,
    repository: IAlquilerPeliculasRepository,
    viewModel: AlquilarDevolverPeliculasViewModel = viewModel(
        factory = AlquilarDevolverPeliculasViewModelFactory(userId, peliculaId, repository)
    ),
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    var showDialog by remember { mutableStateOf(false) }

    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(colorVioleta, colorAzulOscurso),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    CustomScreenWithoutScaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth().background(toolbarBackGround)) {
                Column(modifier = Modifier.statusBarsPadding()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    toolBar(
                        onBackClick = onBackClick,
                        onHomeClick = onHomeClick,
                        onCameraClick = onCameraClick,
                        onProfileClick = onProfileClick,
                        onLogoutClick = onLogoutClick
                    )
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(toolbarBackGround)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.alquiler_peliculas),
                style = typography.headlineLarge.copy(color = colors.primary),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Mostrar la película seleccionada
            AlquilerDevolverPeliculas(peliculas = uiState)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            BotonAlquilarPeliculas(
                botonAlquilar = ButtonData(nombre = R.string.alquilar, type = ButtonType.PRIMARY),
                botonDevolver = ButtonData(nombre = R.string.devolver, type = ButtonType.SECONDARY),
                onAlquilarClick = {
                    viewModel.alquilarPelicula()
                    showDialog = true
                },
                onDevolverClick = {
                    viewModel.devolverPelicula()
                    showDialog = true
                },
                isAlquilarButtonEnabled = uiState.isAlquilarButtonEnabled,
                isDevolverButtonEnabled = uiState.isDevolverButtonEnabled
            )
        }

        if (showDialog) {
            AlquilarDevolverDialog(
                isAlquiler = uiState.peliculaAlquilada,
                fechaAlquiler = uiState.fechaAlquiler,
                fechaDevolucion = uiState.fechaDevolucion,
                onConfirmClick = { showDialog = false }
            )
        }
    }
}

@Composable
fun AlquilarDevolverPeliculasScreen(
    userId: String,
    peliculaId: String,  // String ID como "peli_001"
    repository: IAlquilerPeliculasRepository,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    // ViewModel creado INTERNAMENTE, no como parámetro
    val viewModel: AlquilarDevolverPeliculasViewModel = viewModel(
        factory = AlquilarDevolverPeliculasViewModelFactory(
            userId = userId,
            peliculaId = peliculaId,
            repository = repository
        )
    )

    _AlquilarDevolverPeliculasScreenContent(
        viewModel = viewModel,
        onBackClick = onBackClick,
        onHomeClick = onHomeClick,
        onCameraClick = onCameraClick,
        onProfileClick = onProfileClick,
        onLogoutClick = onLogoutClick
    )
}

// Función interna compartida para la app real y el Preview
@Composable
private fun _AlquilarDevolverPeliculasScreenContent(
    viewModel: AlquilarDevolverPeliculasViewModel,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    var showDialog by remember { mutableStateOf(false) }

    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(colorVioleta, colorAzulOscurso),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    CustomScreenWithoutScaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth().background(toolbarBackGround)) {
                Column(modifier = Modifier.statusBarsPadding()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    toolBar(
                        onBackClick = onBackClick,
                        onHomeClick = onHomeClick,
                        onCameraClick = onCameraClick,
                        onProfileClick = onProfileClick,
                        onLogoutClick = onLogoutClick
                    )
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(toolbarBackGround)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.alquiler_peliculas),
                style = typography.headlineLarge.copy(color = colors.primary),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Mostrar la película seleccionada
            AlquilerDevolverPeliculas(peliculas = uiState)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            BotonAlquilarPeliculas(
                botonAlquilar = ButtonData(nombre = R.string.alquilar, type = ButtonType.PRIMARY),
                botonDevolver = ButtonData(nombre = R.string.devolver, type = ButtonType.SECONDARY),
                onAlquilarClick = {
                    viewModel.alquilarPelicula()
                    showDialog = true
                },
                onDevolverClick = {
                    viewModel.devolverPelicula()
                    showDialog = true
                },
                isAlquilarButtonEnabled = uiState.isAlquilarButtonEnabled,
                isDevolverButtonEnabled = uiState.isDevolverButtonEnabled
            )
        }

        if (showDialog) {
            AlquilarDevolverDialog(
                isAlquiler = uiState.peliculaAlquilada,
                fechaAlquiler = uiState.fechaAlquiler,
                fechaDevolucion = uiState.fechaDevolucion,
                onConfirmClick = { showDialog = false }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilarDevolverPeliculasScreenPreview() {
    val repository = AlquilerPeliculasRepositoryInMemory()
    val userId = "user123"
    val peliculaId = "peli_001"  // ID String correcto, NO resource ID

    // Crear ViewModel manualmente para el Preview
    val viewModel = remember {
        AlquilarDevolverPeliculasViewModel(
            userId = userId,
            peliculaId = peliculaId,  // Usar peliculaId (String)
            repository = repository
        )
    }

    MaterialTheme {
        _AlquilarDevolverPeliculasScreenContent(
            viewModel = viewModel,
            onBackClick = {},
            onHomeClick = {},
            onCameraClick = {},
            onProfileClick = {},
            onLogoutClick = {}
        )
    }
}

// Versión alternativa del Preview que prueba la pantalla completa
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilarDevolverPeliculasScreenFullPreview() {
    val repository = AlquilerPeliculasRepositoryInMemory()

    MaterialTheme {
        AlquilarDevolverPeliculasScreen(
            userId = "user123",
            peliculaId = "peli_001",  // ID String
            repository = repository,
            onBackClick = {},
            onHomeClick = {},
            onCameraClick = {},
            onProfileClick = {},
            onLogoutClick = {}
        )
    }
}