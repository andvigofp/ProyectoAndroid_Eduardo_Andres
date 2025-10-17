package com.example.proyecto_eduardo_andres.componentes.componenteLogin

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
    onAccederClick: () -> Unit,
    onCrearUsuarioClick: () -> Unit,
    onRecuperarPasswordClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón principal: ACCEDER
        Button(
            onClick = onAccederClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50), // Verde brillante
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(55.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "ACCEDER",
                fontSize = 18.sp
            )
        }

        // Fila inferior: CREAR USUARIO y RECUPERAR PASSWORD
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // CREAR USUARIO
            Button(
                onClick = onCrearUsuarioClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3), // Azul fuerte
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "CREAR USUARIO",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // RECUPERAR PASSWORD (ajustado)
            Button(
                onClick = onRecuperarPasswordClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800), // Naranja llamativo
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "RECUPERAR PASSWORD",
                    fontSize = 12.sp, // más pequeño
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
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
                    onAccederClick = {},
                    onCrearUsuarioClick = {},
                    onRecuperarPasswordClick = {}
                )
            }
        }
    }
}
