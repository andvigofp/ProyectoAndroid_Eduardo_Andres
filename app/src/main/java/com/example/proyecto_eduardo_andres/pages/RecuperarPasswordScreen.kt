@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.pages

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componenteRecuperarPassword.RecuperarPasswordButton
import com.example.proyecto_eduardo_andres.viewData.RecuperarPasswordData.RecuperarPasswordButtonText
import com.example.proyecto_eduardo_andres.viewData.RecuperarPasswordData.RecuperarPasswordData
import com.example.proyecto_eduardo_andres.myComponents.componenteRecuperarPassword.RecuperarPasswordFields

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarPasswordScreen(
    onRecuperarClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
) {
    var recuperarPasswordData by remember { mutableStateOf(RecuperarPasswordData()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Degradado superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF3F51B5),
                            Color(0xFF512DA8)
                        )
                    )
                )
        )

        // Degradado inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF512DA8),
                            Color(0xFF3F51B5)
                        )
                    )
                )
        )

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 95.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Título
            Text(
                text = stringResource(R.string.repetir_password),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )


            // Tarjeta del formulario
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(Color.White, RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                // Imagen circular
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .shadow(8.dp, CircleShape)
                        .background(Color(0xFFE3F2FD), CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logotipo_team),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }

                // Campos de recuperación
                RecuperarPasswordFields(
                    recuperarPasswordData = recuperarPasswordData,
                    onRecuperarPasswordData = { recuperarPasswordData = it }
                )
                Spacer(modifier = Modifier.height(32.dp))
                // Botones
                RecuperarPasswordButton(
                    buttonText = RecuperarPasswordButtonText(
                        cancelar = stringResource(R.string.cancelar),
                        recuperarPassword = stringResource(R.string.recuperar)
                    ),
                    onRecuperarClick = onRecuperarClick,
                    onCancelarClick = onCancelarClick
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecuperarPasswordScreendPreview() {
    MaterialTheme {
        RecuperarPasswordScreen()
    }
}