package com.example.proyecto_eduardo_andres.myComponents.componenteLogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginButtons(
    loginButtonTexts: loginButtonTexts,
    onAccederClick: () -> Unit,
    onCrearUsuarioClick: () -> Unit,
    onRecuperarPasswordClick: () -> Unit
) {
    // Si el data class viene vacío, damos los textos por defecto aquí
    val accederText = if (loginButtonTexts.acceder.isNotEmpty()) loginButtonTexts.acceder else "ACCEDER"
    val crearUsuarioText = if (loginButtonTexts.crearUsuario.isNotEmpty()) loginButtonTexts.crearUsuario else "CREAR USUARIO"
    val recuperarText = if (loginButtonTexts.recuperarPassword.isNotEmpty()) loginButtonTexts.recuperarPassword else "RECUPERAR CONTRASEÑA"

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ACCEDER
        Button(
            onClick = onAccederClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
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
fun LoginButtonsPreview() {
    MaterialTheme {
        Surface(color = Color(0xFFF5F5F5)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoginButtons(
                    loginButtonTexts = loginButtonTexts(),
                    onAccederClick = {},
                    onCrearUsuarioClick = {},
                    onRecuperarPasswordClick = {}
                )
            }
        }
    }
}
