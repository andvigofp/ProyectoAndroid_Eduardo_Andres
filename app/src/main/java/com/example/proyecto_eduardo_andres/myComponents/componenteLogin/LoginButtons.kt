@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteLogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewData.LogingData.LoginButtonTextsData

/**
 * @author Eduardo
 * @see Componenente Button Loging
 * @param loginButtonTexts: El botón Acceder si los datos son correctos, inicia la sesión, te llevara a la pantalla de VideclubOnlinePeliculas
 * @param loginButtonTexts: El botón Crear Usuario te lleva a la pantalla de Crear Usuario
 * @param loginButtonTexts: El botón Recuperar contraseña te lleva a la pantalla Recuperar contraseña
 *
 */
@Composable
fun LoginButtons(
    loginButtonTexts: LoginButtonTextsData,
    onAccederClick: () -> Unit,
    onCrearUsuarioClick: () -> Unit,
    onRecuperarPasswordClick: () -> Unit
) {
    // Si el data class viene vacío, damos los textos por defecto aquí
    val accederText = if (loginButtonTexts.acceder.isNotEmpty()) loginButtonTexts.acceder else stringResource(R.string.acceder)
    val crearUsuarioText = if (loginButtonTexts.crearUsuario.isNotEmpty()) loginButtonTexts.crearUsuario else stringResource(R.string.crear_usuario)
    val recuperarText = if (loginButtonTexts.recuperarPassword.isNotEmpty()) loginButtonTexts.recuperarPassword else stringResource(R.string.recuperar_contrasenha)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ACCEDER
        Button(
            onClick = onAccederClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text(text = accederText, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fila inferior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onCrearUsuarioClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text(
                    text = crearUsuarioText,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = onRecuperarPasswordClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text(
                    text = recuperarText,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginButtonPreview() {
    MaterialTheme {
        Surface(color = Color(0xFFF5F5F5)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoginButtons(
                    loginButtonTexts = LoginButtonTextsData(),
                    onAccederClick = {},
                    onCrearUsuarioClick = {},
                    onRecuperarPasswordClick = {}
                )
            }
        }
    }
}
