@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.pagina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.IRecuperarPasswordRepository
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.ConfirmationDialog
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.InfoDialog
import com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.RecuperarPasswordRepositoryInMemory
import com.example.proyecto_eduardo_andres.modelo.RecuperarPasswordButtonDto
import com.example.proyecto_eduardo_andres.viewmodel.vm.RecuperarPasswordViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.RecuperarPasswordViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteRecuperarPassword.RecuperarPasswordButton
import com.example.proyecto_eduardo_andres.vista.componente.componenteRecuperarPassword.RecuperarPasswordFields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarPasswordScreen(
    repository: IRecuperarPasswordRepository,
    recuperarPasswordViewModel: RecuperarPasswordViewModel = viewModel(
        factory = RecuperarPasswordViewModelFactory(repository)
    ),
    onRecuperarClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
) {
    val uiState by recuperarPasswordViewModel.uiState.collectAsState()

    // Local state para formularios y sincronización
    var recuperarPasswordData by remember { mutableStateOf(uiState) }

    LaunchedEffect(uiState) {
        recuperarPasswordData = uiState
    }

    // Estado local para mostrar diálogo de confirmación antes de ejecutar la acción
    var showConfirmDialog by remember { mutableStateOf(false) }

    val colors = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        // Degradados superior e inferior (usa colores de tema si quieres)
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 95.dp, bottom = 32.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.recuperar_contrasenha),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onPrimary,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(colors.surface, RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
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
                        modifier = Modifier.size(80.dp).clip(CircleShape)
                    )
                }

                RecuperarPasswordFields(
                    recuperarPasswordData = recuperarPasswordData,
                    onRecuperarPasswordData = {
                        recuperarPasswordData = it

                        // Actualizar ViewModel para mantener estado consistente
                        recuperarPasswordViewModel.onEmailChange(it.email)
                        recuperarPasswordViewModel.onPasswordChange(it.password)
                        recuperarPasswordViewModel.onRepeatPasswordChange(it.repeatPassword)
                    }
                )

                // Después de los campos
                Spacer(modifier = Modifier.height(48.dp))  // Aumenté a 48.dp para más espacio

                RecuperarPasswordButton(
                    buttonText = RecuperarPasswordButtonDto(
                        cancelar = R.string.cancelar,
                        recuperarPassword = R.string.recuperar
                    ),
                    enabled = uiState.isLoginButtonEnabled,
                    onRecuperarClick = {
                        // Mostrar diálogo de confirmación antes de realizar la acción
                        showConfirmDialog = true
                    },
                    onCancelarClick = onCancelarClick
                )
            }
        }

        // Diálogo de confirmación: si el usuario confirma se llama al ViewModel
        ConfirmationDialog(
            showDialog = showConfirmDialog,
            onDismissRequest = { showConfirmDialog = false },
            title = stringResource(R.string.recuperar_contrasenha),
            message = stringResource(R.string.confirm_recuperar_password_message),
            confirmButtonText = stringResource(R.string.recuperar),
            dismissButtonText = stringResource(R.string.cancelar),
            onConfirmClick = {
                showConfirmDialog = false
                recuperarPasswordViewModel.recuperarPassword(
                    onSuccess = onRecuperarClick,
                    onError = { /* manejar error si quieres */ }
                )
            },
            onDismissClick = { showConfirmDialog = false }
        )

        // Diálogo informativo/respuesta (igual que en LogingScreen)
        InfoDialog(
            showDialog = recuperarPasswordViewModel.showLoginDialog,
            onDismissRequest = { recuperarPasswordViewModel.dismissDialog() },
            title = recuperarPasswordViewModel.dialogTitle,
            message = recuperarPasswordViewModel.loginMessage
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecuperarPasswordScreenPreview() {
    val repository = RecuperarPasswordRepositoryInMemory()
    MaterialTheme {
        RecuperarPasswordScreen(
            repository = repository
        )
    }
}