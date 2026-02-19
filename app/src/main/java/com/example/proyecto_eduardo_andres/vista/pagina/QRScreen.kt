@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.pagina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_eduardo_andres.data.repository.qrRepository.IQRRepository
import com.example.proyecto_eduardo_andres.data.repository.qrRepository.QRRepositoryInMemory
import com.example.proyecto_eduardo_andres.modelo.QRDto
import com.example.proyecto_eduardo_andres.viewmodel.vm.QRViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.QRViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteQR.QRView
import com.example.proyecto_eduardo_andres.vista.componente.componenteToolbar.toolBar

/**
 * @author Eduardo
 *
 * Pantalla encargada de generar y mostrar el código QR
 * asociado al usuario actual.
 *
 * Esta pantalla:
 * - Crea internamente su ViewModel mediante Factory.
 * - Observa el estado reactivo con StateFlow.
 * - Muestra indicador de carga mientras obtiene los datos.
 * - Muestra mensaje de error si ocurre un fallo.
 * - Renderiza el código QR cuando los datos están disponibles.
 * - Incluye Toolbar superior y BottomBar inferior.
 *
 * Arquitectura:
 * - Sigue patrón MVVM.
 * - El ViewModel gestiona la obtención de los datos QR.
 * - La UI representa el estado sin contener lógica de negocio.
 *
 * @param userId Identificador único del usuario.
 * @param repository Repositorio encargado de obtener los datos del QR.
 * @param viewModel ViewModel encargado de gestionar el estado del QR.
 * @param onBackClick Callback navegación atrás.
 * @param onHomeClick Callback navegación Home.
 * @param onCameraClick Callback navegación Cámara.
 * @param onProfileClick Callback navegación Perfil.
 * @param onLogoutClick Callback cerrar sesión.
 *
 * @see QRViewModel
 * @see QRViewModelFactory
 * @see StateFlow
 * @see collectAsState
 * @see CircularProgressIndicator
 */
@Composable
fun QRScreen(
    userId: String,
    repository: IQRRepository,
    viewModel: QRViewModel = viewModel(
        factory = QRViewModelFactory(repository, userId)
    ),
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        // ---------- TOOLBAR ----------
        Column {
            toolBar(
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onCameraClick = onCameraClick,
                onProfileClick = onProfileClick,
                onLogoutClick = onLogoutClick
            )
        }

        // ---------- CONTENIDO PRINCIPAL ----------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp, bottom = 56.dp), // dejar espacio para top y bottom
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }
                uiState.error != null -> {
                    Text(text = uiState.error ?: "")
                }
                else -> {
                    QRView(
                        qrData = QRDto(uiState.qrData)
                    )
                }
            }
        }

        // ---------- BOTTOM BAR ----------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QRScreenPreview() {
    val repository = QRRepositoryInMemory()
    val viewModel = QRViewModel(repository, 1.toString())
    viewModel.setQRData("https://preview-qr.com")

    QRScreen(
        userId = 1.toString(),
        repository = repository,
        viewModel = viewModel,
        onBackClick = {},
        onHomeClick = {},
        onCameraClick = {},
        onProfileClick = {},
        onLogoutClick = {}
    )
}