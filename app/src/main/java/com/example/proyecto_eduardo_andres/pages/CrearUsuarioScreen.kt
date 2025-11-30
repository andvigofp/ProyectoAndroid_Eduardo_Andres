@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.pages

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario.CampoCrearUsuario
import com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario.CrearUsuarioData
import com.example.proyecto_eduardo_andres.myComponents.componenteButtons.AppButton
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonData
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonType

@Composable
fun CrearUsuarioScreen(
    onCrearUsuarioClick: () -> Unit = {},
    onCancelarClick: () -> Unit = {}
) {
    var crearUsuarioData by remember { mutableStateOf(CrearUsuarioData()) }
    val scrollState = rememberScrollState()
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        // --- Degradado superior (toolbar visual) ---
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

        // --- Degradado inferior (fondo detrás de botones) ---
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

        // --- Contenido principal con scroll ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 95.dp, bottom = 32.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Título principal
            Text(
                text = stringResource(R.string.crear_usuario),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onPrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Tarjeta del formulario ---
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(colors.surface, RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Imagen circular del logotipo
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

                // Campos del formulario
                CampoCrearUsuario(
                    crearUsuarioData = crearUsuarioData,
                    onCrearUsuarioData = { crearUsuarioData = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botones usando AppButton
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

                    Spacer(modifier = Modifier.width(16.dp))

                    AppButton(
                        data = ButtonData(
                            nombre = R.string.crear,
                            type = ButtonType.PRIMARY
                        ),
                        onClick = onCrearUsuarioClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrearUsuarioScreenPreview() {
    MaterialTheme {
        CrearUsuarioScreen()
    }
}