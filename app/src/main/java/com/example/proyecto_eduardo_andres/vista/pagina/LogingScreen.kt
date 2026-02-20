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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.viewmodel.vm.LoginViewModel
import com.example.proyecto_eduardo_andres.vista.componente.componenteLogin.LoginButtons
import androidx.compose.material3.MaterialTheme
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.InfoDialog
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.vista.componente.componenteLogin.LoginMode

/**
 * @author Andrés
 *
 * Pantalla de autenticación (Login) de la aplicación.
 *
 * Esta pantalla:
 * - Observa el estado reactivo del LoginViewModel.
 * - Permite introducir credenciales de acceso.
 * - Gestiona la opción "Mantener sesión iniciada".
 * - Muestra diálogos informativos en caso de error o éxito.
 * - Ejecuta navegación tras autenticación correcta.
 *
 * Arquitectura:
 * - Sigue patrón MVVM.
 * - El ViewModel contiene la lógica de autenticación.
 * - La UI es declarativa y basada en estado.
 *
 * Diseño:
 * - Utiliza MaterialTheme (Material3).
 * - Implementa degradados superior e inferior.
 * - Usa componentes reutilizables (CamposLogin, LoginButtons).
 *
 * @param loginViewModel ViewModel encargado de la lógica de login.
 * @param onCrearUsuarioClick Callback hacia pantalla de registro.
 * @param onRecuperarPasswordClick Callback hacia recuperación de contraseña.
 * @param onLoginSuccess Callback ejecutado tras autenticación exitosa.
 *
 * @see LoginViewModel
 * @see StateFlow
 * @see collectAsState
 * @see LaunchedEffect
 * @see InfoDialog
 */
@Composable
fun LogingScreen(
    loginViewModel: LoginViewModel,
    onCrearUsuarioClick: () -> Unit,
    onRecuperarPasswordClick: () -> Unit,
    onLoginSuccess: (String) -> Unit,
) {

    val uiState by loginViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        loginViewModel.startServerMonitoring()
    }

    DisposableEffect(Unit) {
        onDispose {
            loginViewModel.stopServerMonitoring()
        }
    }

    // Navegación cuando login es exitoso
    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            loginViewModel.loggedInUserId?.let {
                onLoginSuccess(it)
            }
        }
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceBright)
    ) {

        // ------------------------
        // DEGRADADOS
        // ------------------------

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(colorVioleta, colorAzulOscurso)
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
                        listOf(colorVioleta, colorAzulOscurso)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 95.dp, bottom = 32.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // TÍTULO
            Text(
                text = stringResource(R.string.loging),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )


            // INDICADOR DE MODO (REACTIVO)
            Text(
                text = if (uiState.loginMode == LoginMode.ROOM)
                    "Modo sin conexión (Invitado)"
                else
                    "Modo online",
                color = if (uiState.loginMode == LoginMode.ROOM)
                    Color.Yellow
                else
                    Color.Green,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))


            // TARJETA
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(
                        MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(24.dp)
                    )
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {


                // LOGO
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .shadow(8.dp, CircleShape)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logotipo_team),
                        contentDescription = stringResource(R.string.logo),
                        modifier = Modifier.size(80.dp)
                    )
                }


                // CAMPOS (SOLO ONLINE)
                if (uiState.loginMode == LoginMode.RETROFIT) {

                    OutlinedTextField(
                        value = uiState.email,
                        onValueChange = { loginViewModel.onEmailChange(it) },
                        label = { Text("Email") },
                        enabled = !uiState.isLoading,
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = { loginViewModel.onPasswordChange(it) },
                        label = { Text("Password") },
                        singleLine = true,
                        enabled = !uiState.isLoading,
                        visualTransformation =
                            if (uiState.passwordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                        trailingIcon = {
                            TextButton(
                                onClick = {
                                    loginViewModel.togglePasswordVisibility()
                                }
                            ) {
                                Text(
                                    if (uiState.passwordVisible)
                                        "Hide"
                                    else
                                        "Show"
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = uiState.keepLogged,
                            onCheckedChange = {
                                loginViewModel.onKeepLoggedChange(it)
                            },
                            enabled = !uiState.isLoading
                        )
                        Text("Mantener sesión iniciada")
                    }
                }


                // BOTONES
                if (uiState.loginMode == LoginMode.RETROFIT) {

                    LoginButtons(
                        accederButton = ButtonData(
                            nombre = R.string.acceder,
                            type = ButtonType.PRIMARY
                        ),
                        crearUsuarioButton = ButtonData(
                            nombre = R.string.crear_usuario,
                            type = ButtonType.SECONDARY
                        ),
                        recuperarButton = ButtonData(
                            nombre = R.string.recuperar_contrasenha,
                            type = ButtonType.DANGER
                        ),
                        enabledAcceder =
                            uiState.isLoginButtonEnabled &&
                                    !uiState.isLoading,
                        showAccederButton = true,
                        onAccederClick = {
                            loginViewModel.onLoginClicked()
                        },
                        onCrearUsuarioClick = onCrearUsuarioClick,
                        onRecuperarPasswordClick = onRecuperarPasswordClick
                    )

                } else {
                    //MODO OFFLINE (ROOM)

                    Button(
                        onClick = { loginViewModel.onLoginClicked() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text("Entrar como invitado")
                    }
                }
            }


            // DIÁLOGO INFO / ERROR
            InfoDialog(
                showDialog = loginViewModel.showLoginDialog,
                onDismissRequest = { loginViewModel.dismissDialog() },
                title = loginViewModel.dialogTitle,
                message = loginViewModel.loginMessage
            )
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {

    val fakeRepository = UserRepositoryInMemory(
        authApi = RetrofitClient.authApiService
    )


    val previewViewModel = LoginViewModel(
        userRepository = fakeRepository,
    )

    MaterialTheme {
        LogingScreen(
            loginViewModel = previewViewModel,
            onLoginSuccess = {},
            onCrearUsuarioClick = {},
            onRecuperarPasswordClick = {}
        )
    }
}