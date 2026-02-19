@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.pagina

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.repository.camaraRepository.CamaraRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.camaraRepository.ICamaraRepository
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.modelo.CamaraDto
import com.example.proyecto_eduardo_andres.viewmodel.vm.CamaraViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.CamaraViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.InfoDialog
import com.example.proyecto_eduardo_andres.vista.componente.componenteCamara.CamaraButtonsComponent
import com.example.proyecto_eduardo_andres.vista.componente.componenteCamara.CamaraComponent
import com.example.proyecto_eduardo_andres.vista.componente.componenteToolbar.toolBar

/**
 * @author Andrés
 *
 * Pantalla encargada de gestionar la funcionalidad
 * de cámara dentro de la aplicación.
 *
 * Esta pantalla:
 * - Solicita permisos de cámara en tiempo de ejecución.
 * - Lanza la cámara del dispositivo mediante Activity Result API.
 * - Permite mostrar imagen capturada o imagen simulada.
 * - Gestiona errores mostrando un diálogo informativo.
 * - Permite navegación hacia QR u otras pantallas.
 *
 * Arquitectura:
 * - Sigue patrón MVVM.
 * - El ViewModel gestiona el estado reactivo de la imagen.
 * - La UI gestiona permisos y eventos del sistema.
 *
 * Diseño:
 * - Utiliza MaterialTheme para consistencia visual.
 * - Implementa TopBar y BottomBar personalizados.
 * - Usa gradiente para coherencia estética.
 *
 * @param repository Repositorio encargado de preparar
 * la captura de imagen (real o simulada).
 * @param onBackClick Callback de navegación atrás.
 * @param onHomeClick Callback hacia pantalla principal.
 * @param onCameraClick Callback hacia cámara.
 * @param onProfileClick Callback hacia perfil.
 * @param onLogoutClick Callback para cerrar sesión.
 * @param onQrClick Callback hacia pantalla QR.
 *
 * @see CamaraViewModel
 * @see CamaraViewModelFactory
 * @see rememberLauncherForActivityResult
 * @see ActivityResultContracts
 * @see MaterialTheme
 */
@Composable
fun CamaraScreen(
    repository: ICamaraRepository,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onQrClick: () -> Unit = {}
) {

    val viewModel: CamaraViewModel = viewModel(
        factory = CamaraViewModelFactory(repository)
    )

    val context = LocalContext.current

    var permisoConcedido by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permisoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        permisoConcedido = granted
    }

    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var showErrorDialog by remember { mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            viewModel.setImagenUri(photoUri!!)
        } else {
            viewModel.setImagenDrawable(R.drawable.fake_camera_image)
            showErrorDialog = true
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    val colors = MaterialTheme.colorScheme

    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(colorVioleta, colorAzulOscurso),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {

        // ================= TOP BAR =================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(toolbarBackGround)
        ) {
            Column(modifier = Modifier.statusBarsPadding()) {
                Spacer(modifier = Modifier.height(24.dp))
                toolBar(
                    onBackClick = onBackClick,
                    onHomeClick = onHomeClick,
                    onCameraClick = onCameraClick,
                    onProfileClick = onProfileClick,
                    onLogoutClick = onLogoutClick
                )
            }
        }

        // ================= CONTENIDO CENTRAL =================
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CamaraComponent(
                camaraData = CamaraDto(
                    imagenUri = uiState.imagenUri,
                    imagenDrawable = uiState.imagenDrawable,
                    descripcion = R.string.vista_previa_camara
                )
            )
        }

        // ================= BOTTOM BAR =================
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(toolbarBackGround)
                .padding(vertical = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            CamaraButtonsComponent(
                hacerFotoButton = ButtonData(
                    R.string.hacer_foto,
                    ButtonType.PRIMARY
                ),
                qrButton = ButtonData(
                    R.string.qr,
                    ButtonType.SECONDARY
                ),
                onHacerFotoClick = {

                    if (!permisoConcedido) {
                        permisoLauncher.launch(Manifest.permission.CAMERA)
                        return@CamaraButtonsComponent
                    }

                    repository.hacerFoto(
                        onSuccessUri = { uri ->
                            photoUri = uri
                            cameraLauncher.launch(uri)
                        },
                        onSuccessDrawable = { drawable ->
                            viewModel.setImagenDrawable(drawable)
                            showErrorDialog = true
                        },
                        onError = {
                            viewModel.setImagenDrawable(R.drawable.fake_camera_image)
                            showErrorDialog = true
                        }
                    )
                },
                onQrClick = onQrClick
            )
        }

        // ================= ERROR DIALOG =================
        if (showErrorDialog) {
            InfoDialog(
                showDialog = true,
                onDismissRequest = { showErrorDialog = false },
                title = "Cámara no disponible",
                message = "No se pudo abrir la cámara. Se ha cargado imagen simulada."
            )
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CamaraScreenPreview() {
    val repository = CamaraRepositoryInMemory() // Simula cámara

    MaterialTheme {
        CamaraScreen(
            repository = repository,
            onBackClick = {},
            onHomeClick = {},
            onCameraClick = {},
            onProfileClick = {},
            onLogoutClick = {},
            onQrClick = {}
        )
    }
}