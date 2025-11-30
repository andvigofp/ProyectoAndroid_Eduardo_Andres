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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componentePerfilUsuario.CampoPerfilUsuario
import com.example.proyecto_eduardo_andres.viewData.perfilUsuarioData.PerfilUsuarioButtonTextsData
import com.example.proyecto_eduardo_andres.myComponents.componentePerfilUsuario.PerfilUsuarioButtons
import com.example.proyecto_eduardo_andres.viewData.perfilUsuarioData.PerfilUsuarioData
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar

@Composable
fun PerfilUsuarioScreen() {
    var perfilUsuarioData by remember { mutableStateOf(PerfilUsuarioData()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceBright)
    ) {
        // --- Degradado superior ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(colorVioleta, colorAzulOscurso),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f)
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
                    Brush.verticalGradient(listOf(colorVioleta, colorAzulOscurso))
                )
        )

        // --- Contenido principal ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 160.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Tarjeta blanca central
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Imagen circular
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .shadow(8.dp, CircleShape)
                        .background(colorAzulOscurso, CircleShape)
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

                Spacer(modifier = Modifier.height(16.dp))

                // Campos de perfil
                CampoPerfilUsuario(
                    perfilUsarioData = perfilUsuarioData,
                    onPerfilUsuarioData = { perfilUsuarioData = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Texto adicional
                Text(
                    text = stringResource(R.string.peliculas_alquiladas),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón principal usando PerfilUsuarioButtons
                PerfilUsuarioButtons(
                    perfilUsuarioButtonTextsData = PerfilUsuarioButtonTextsData(
                        modificar = stringResource(R.string.modificar_usuario)
                    ),
                    onModificarUsuario = { /* Acción modificar */ }
                )
            }
        }

        // --- TOOLBAR + título ---
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                toolBar(
                    onBackClick = {},
                    onHomeClick = {},
                    onCameraClick = {},
                    onProfileClick = {},
                    onLogoutClick = {}
                )

                Text(
                    text = stringResource(R.string.datos_usuario),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilUsuarioScreenPreview() {
    MaterialTheme {
        PerfilUsuarioScreen()
    }
}
