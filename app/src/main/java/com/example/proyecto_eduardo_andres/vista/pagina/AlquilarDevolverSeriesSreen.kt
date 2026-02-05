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
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.AlquilerSeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.IAlquilerSeriesRepository
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.viewmodel.vm.AlquilarDevolverSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.AlquilarDevolverSeriesViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlquilerDevolverPeliculasSeriesDialog.AlquilarDevolverDialog
import com.example.proyecto_eduardo_andres.vista.componente.componenteAquilarDevolverSeries.AlquilarDevolverSerie
import com.example.proyecto_eduardo_andres.vista.componente.componenteAquilarDevolverSeries.BotonAlquilarSeries
import com.example.proyecto_eduardo_andres.vista.componente.componenteCustomScreenPeliculasSeries.CustomScreenWithoutScaffold
import com.example.proyecto_eduardo_andres.vista.componente.componenteToolbar.toolBar
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlquilerDevolverSeriesScreen(
    userId: String,
    serieId: String,
    repository: IAlquilerSeriesRepository,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    // ViewModel creado internamente, NO como parámetro
    val viewModel: AlquilarDevolverSeriesViewModel = viewModel(
        factory = AlquilarDevolverSeriesViewModelFactory(
            userId = userId,
            serieId = serieId,  // Usar serieId
            repository = repository
        )
    )

    _AlquilerDevolverSeriesScreenContent(
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
private fun _AlquilerDevolverSeriesScreenContent(
    viewModel: AlquilarDevolverSeriesViewModel,
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
                text = stringResource(R.string.alquiler_serie),
                style = typography.headlineLarge.copy(color = colors.primary),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            AlquilarDevolverSerie(series = uiState)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            BotonAlquilarSeries(
                botonAlquilar = ButtonData(nombre = R.string.alquilar, type = ButtonType.PRIMARY),
                botonDevolver = ButtonData(nombre = R.string.devolver, type = ButtonType.SECONDARY),
                onAlquilarClick = {
                    viewModel.alquilarSerie()
                    showDialog = true
                },
                onDevolverClick = {
                    viewModel.devolverSerie()
                    showDialog = true
                },
                isAlquilarButtonEnabled = uiState.isAlquilarButtonEnabled,
                isDevolverButtonEnabled = uiState.isDevolverButtonEnabled
            )
        }

        if (showDialog) {
            val fechaLimiteDevolucion = uiState.fechaAlquiler?.let {
                Calendar.getInstance().apply {
                    time = it
                    add(Calendar.DAY_OF_MONTH, 7)
                }.time
            }

            val esMulta = if (!uiState.serieAlquilada && fechaLimiteDevolucion != null) {
                uiState.fechaDevolucion?.after(fechaLimiteDevolucion) ?: Date().after(fechaLimiteDevolucion)
            } else {
                false
            }

            AlquilarDevolverDialog(
                isAlquiler = uiState.serieAlquilada,
                fechaAlquiler = uiState.fechaAlquiler,
                fechaDevolucion = uiState.fechaDevolucion,
                onConfirmClick = { showDialog = false },
                fechaLimiteDevolucion = fechaLimiteDevolucion,
                esMulta = esMulta
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlquilerDevolverSeriesScreenPreview() {
    val repository = AlquilerSeriesRepositoryInMemory()
    val userId = "user123"
    val serieId = "serie_001"

    // Crear ViewModel manualmente para el Preview
    val viewModel = remember {
        AlquilarDevolverSeriesViewModel(
            userId = userId,
            serieId = serieId,  // Usar serieId (String)
            repository = repository
        )
    }

    MaterialTheme {
        _AlquilerDevolverSeriesScreenContent(
            viewModel = viewModel,
            onBackClick = {},
            onHomeClick = {},
            onCameraClick = {},
            onProfileClick = {},
            onLogoutClick = {}
        )
    }
}