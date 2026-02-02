@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.pagina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteLogin.CamposLogin
import com.example.proyecto_eduardo_andres.vista.componente.componenteLogin.LoginButtons
import androidx.compose.material3.MaterialTheme
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.InfoDialog


@Composable
fun LogingScreen(
    loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(UserRepositoryInMemory(RetrofitClient.authApiService))
    ),
    onAccederClick: () -> Unit,
    onCrearUsuarioClick: () -> Unit,
    onRecuperarPasswordClick: () -> Unit
) {
    val uiState by loginViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceBright)
    ) {
        // --- Degradados superior e inferior ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Brush.verticalGradient(listOf(colorVioleta, colorAzulOscurso)))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.BottomCenter)
                .background(Brush.verticalGradient(listOf(colorVioleta, colorAzulOscurso)))
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
            // Título LOGIN
            Text(
                text = stringResource(R.string.loging),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Tarjeta blanca
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                // Logo circular
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .shadow(8.dp, CircleShape)
                        .background(MaterialTheme.colorScheme.primary, CircleShape)
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

                Spacer(modifier = Modifier.height(16.dp))

                // Campos de login
                CamposLogin(
                    loginData = uiState,
                    onLoginDataChange = {
                        loginViewModel.onEmailChange(it.email)
                        loginViewModel.onPasswordChange(it.password)
                    },
                    onTogglePasswordVisibility = { loginViewModel.togglePasswordVisibility() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botones
                LoginButtons(
                    accederButton = ButtonData(nombre = R.string.acceder, type = ButtonType.PRIMARY),
                    crearUsuarioButton = ButtonData(nombre = R.string.crear_usuario, type = ButtonType.SECONDARY),
                    recuperarButton = ButtonData(nombre = R.string.recuperar_contrasenha, type = ButtonType.DANGER),
                    enabledAcceder = uiState.isLoginButtonEnabled,
                    onAccederClick = {
                        loginViewModel.logging {
                            onAccederClick() // navegación
                        }
                    },
                    onCrearUsuarioClick = onCrearUsuarioClick,
                    onRecuperarPasswordClick = onRecuperarPasswordClick
                )
            }
        }
    }


    // Diálogo de información general
    InfoDialog(
        showDialog = loginViewModel.showLoginDialog,
        onDismissRequest = { loginViewModel.dismissDialog() },
        title = loginViewModel.dialogTitle,
        message = loginViewModel.loginMessage
    )
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LogingScreen(
            onAccederClick = { println("Acceder clic - login con usuario de ejemplo") },
            onCrearUsuarioClick = {},
            onRecuperarPasswordClick = {}
        )
    }
}