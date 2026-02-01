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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario.CampoCrearUsuario
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.repository.crearUsuario.CrearUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.viewmodel.vm.CrearUsuarioViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.CrearUsuarioViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteButtons.AppButton

@Composable
fun CrearUsuarioScreen(
    crearUsuarioViewModel: CrearUsuarioViewModel = viewModel(
        factory = CrearUsuarioViewModelFactory(
            CrearUsuarioRepositoryInMemory(RetrofitClient.authApiService)
        )
    ),
    onCrearUsuarioClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
) {
    // --- Estado del ViewModel ---
    val uiState by crearUsuarioViewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

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
                        modifier = Modifier.size(80.dp).clip(CircleShape)
                    )
                }

                // --- Campos del formulario ---
                CampoCrearUsuario(
                    crearUsuarioData = uiState,
                    onCrearUsuarioData = { data ->
                        crearUsuarioViewModel.onNameChange(data.nombre)
                        crearUsuarioViewModel.onPasswordChange(data.password)
                        crearUsuarioViewModel.onRepeatPasswordChange(data.repeatPassword)
                        crearUsuarioViewModel.onEmailChange(data.email)
                        crearUsuarioViewModel.onRepeatEmailChange(data.repeatEmail)
                    },
                    onTogglePasswordVisibility = {
                        crearUsuarioViewModel.togglePasswordVisibility()
                    },
                    onToggleRepeatPasswordVisibility = {
                        crearUsuarioViewModel.toggleRepeatPasswordVisibility()
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
                            crearUsuarioViewModel.crearUsuario(
                                onSuccess = {
                                },
                                onError = { error ->
                                    Log.e("CrearUsuario", error.message ?: "Error desconocido")
                                }
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    )
                }
            }
        }
    }
    // --- AlertDialog Crear Usuario ---
    if (crearUsuarioViewModel.showCrearUsuarioDialog) {
        AlertDialog(
            onDismissRequest = {
                crearUsuarioViewModel.dismissDialog()
                // Solo navegar si el mensaje indica éxito
                if (crearUsuarioViewModel.crearUsuarioMessage.contains("correctamente")) {
                    onCrearUsuarioClick()
                }
            },
            title = { Text(text = "Crear Usuario") },
            text = { Text(text = crearUsuarioViewModel.crearUsuarioMessage) },
            confirmButton = {
                Button(
                    onClick = {
                        crearUsuarioViewModel.dismissDialog()
                        if (crearUsuarioViewModel.crearUsuarioMessage.contains("correctamente")) {
                            onCrearUsuarioClick()
                        }
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrearUsuarioScreenPreview() {
    MaterialTheme {
        CrearUsuarioScreen()
    }
}