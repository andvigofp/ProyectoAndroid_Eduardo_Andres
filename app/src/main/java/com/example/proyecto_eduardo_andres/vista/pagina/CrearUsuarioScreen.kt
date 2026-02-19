@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.pagina

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.repository.crearUsuario.CrearUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario.CampoCrearUsuario
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.viewmodel.vm.CrearUsuarioViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.CrearUsuarioViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteButtons.AppButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.ConfirmationDialog
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.InfoDialog
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import com.example.proyecto_eduardo_andres.data.repository.crearUsuario.ICrearUsuarioRepository
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService

/**
 * @author Andrés
 *
 * Pantalla encargada de la creación de nuevos usuarios.
 *
 * Esta pantalla:
 * - Crea internamente su ViewModel mediante Factory.
 * - Observa el estado reactivo del formulario.
 * - Permite introducir datos de registro.
 * - Muestra un diálogo de confirmación antes de crear el usuario.
 * - Muestra un diálogo informativo tras éxito o error.
 * - Gestiona navegación tras creación exitosa.
 *
 * Arquitectura:
 * - Sigue patrón MVVM.
 * - El ViewModel contiene la lógica de validación y persistencia.
 * - La UI es declarativa y reactiva.
 *
 * Diseño:
 * - Utiliza MaterialTheme para consistencia visual.
 * - Implementa degradados superior e inferior.
 * - Usa componentes reutilizables (CampoCrearUsuario, AppButton).
 *
 * @param repository Repositorio encargado de crear el usuario.
 * @param onCrearUsuarioSucess Callback ejecutado tras creación exitosa.
 * @param onCancelarClick Callback ejecutado al cancelar el registro.
 *
 * @see CrearUsuarioViewModel
 * @see CrearUsuarioViewModelFactory
 * @see ConfirmationDialog
 * @see InfoDialog
 * @see StateFlow
 * @see collectAsState
 */
@Composable
fun CrearUsuarioScreen(
    repository: ICrearUsuarioRepository,
    onCrearUsuarioSucess: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
) {
    val viewModel: CrearUsuarioViewModel = viewModel(
        factory = CrearUsuarioViewModelFactory( repository) )

    // --- Estado del ViewModel ---
    val uiState by viewModel.uiState.collectAsState()

    // Estado local para mostrar el diálogo de confirmación
    var showConfirmDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val colors = MaterialTheme.colorScheme
    // val typography = MaterialTheme.typography // no utilizado aquí

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {

        // --- Degradado superior ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(colors.primary, colors.secondary)
                    )
                )
        )

        // --- Degradado inferior ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(colors.primary, colors.secondary)
                    )
                )
        )

        // --- Contenido principal ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 95.dp, bottom = 32.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = stringResource(R.string.crear_usuario),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onPrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Tarjeta ---
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(colors.surface, RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .shadow(8.dp, CircleShape)
                        .background(colors.primary, CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logotipo_team),
                        contentDescription = stringResource(R.string.logo),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }

                // --- Campos del formulario ---
                CampoCrearUsuario(
                    crearUsuarioData = uiState,
                    onCrearUsuarioData = { data ->
                        viewModel.onNameChange(data.nombre)
                        viewModel.onPasswordChange(data.password)
                        viewModel.onRepeatPasswordChange(data.repeatPassword)
                        viewModel.onEmailChange(data.email)
                        viewModel.onRepeatEmailChange(data.repeatEmail)
                    },
                    onTogglePasswordVisibility = {
                        viewModel.togglePasswordVisibility()
                    },
                    onToggleRepeatPasswordVisibility = {
                        viewModel.toggleRepeatPasswordVisibility()
                    }
                )


                Spacer(modifier = Modifier.height(24.dp))

                // --- Botones (MISMO layout) ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    AppButton(
                        data = ButtonData(
                            nombre = R.string.cancelar,
                            type = ButtonType.SECONDARY
                        ),
                        onClick = onCancelarClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    AppButton(
                        data = ButtonData(
                            nombre = R.string.crear,
                            type = ButtonType.PRIMARY,
                            enabled = uiState.isLoginButtonEnabled
                        ),
                        onClick = {
                            // Mostrar diálogo de confirmación antes de crear usuario
                            showConfirmDialog = true
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    )
                }
            }
        }
    }
    // --- ConfirmationDialog: preguntar antes de crear usuario ---
    ConfirmationDialog(
        showDialog = showConfirmDialog,
        onDismissRequest = { showConfirmDialog = false },
        title = stringResource(R.string.crear_usuario),
        message = stringResource(R.string.confirm_crear_usuario_message),
        confirmButtonText = stringResource(R.string.crear),
        dismissButtonText = stringResource(R.string.cancelar),
        onConfirmClick = {
            showConfirmDialog = false
            viewModel.crearUsuario(
                onSuccess = { user ->
                    Log.d("CrearUsuario", "Usuario creado: ${user.name} (ID: ${user.id})")
                },
                onError = { error ->
                    Log.e("CrearUsuario", error.message ?: "Error desconocido")
                }
            )
        },
        onDismissClick = { showConfirmDialog = false }
    )

    // --- InfoDialog Crear Usuario (reutiliza el componente) ---
    InfoDialog(
        showDialog = viewModel.showCrearUsuarioDialog,
        onDismissRequest = {
            viewModel.dismissDialog()
        },
        title = viewModel.dialogTitle,
        message = viewModel.crearUsuarioMessage
    )

    // Navegación cuando el InfoDialog se cierra (auto-dismiss o manual)
    val currentOnCrear = rememberUpdatedState(onCrearUsuarioSucess)
    LaunchedEffect(viewModel.showCrearUsuarioDialog) {
        // Si el diálogo acabó de cerrarse y el último mensaje fue éxito, navegar
        if (!viewModel.showCrearUsuarioDialog && viewModel.crearUsuarioMessage.contains("correctamente")) {
            currentOnCrear.value()
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrearUsuarioScreenPreview() {

    val repository = CrearUsuarioRepositoryInMemory(
        authApi = RetrofitClient.authApiService
    )

    MaterialTheme {
        CrearUsuarioScreen(
            repository = repository,
            onCrearUsuarioSucess = {},
            onCancelarClick = {}
        )
    }
}