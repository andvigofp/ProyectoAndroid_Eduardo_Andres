@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.viewData.CamaraData.CamaraButtonData
import com.example.proyecto_eduardo_andres.myComponents.componenteCamara.CamaraButtonsComponent
import com.example.proyecto_eduardo_andres.myComponents.componenteCamara.CamaraComponent
import com.example.proyecto_eduardo_andres.viewData.CamaraData.CamaraData
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar
import com.example.proyecto_eduardo_andres.R

@Composable
fun CamaraScreen() {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(
            colorVioleta,
            colorAzulOscurso
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        // TopBar con degradado que cubre el reloj + toolbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(toolbarBackGround)
        ) {
            Column(
                modifier = Modifier.statusBarsPadding()
            ) {
                Spacer(modifier = Modifier.height(24.dp)) // espacio visual del reloj
                toolBar(
                    onBackClick = { /* TODO: volver */ },
                    onHomeClick = { /* TODO: ir al inicio */ },
                    onCameraClick = { /* TODO: recargar cámara */ },
                    onProfileClick = { /* TODO: perfil */ },
                    onLogoutClick = { /* TODO: cerrar sesión */ }
                )
            }
        }

        // Contenido centrado en la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp) // espacio para bottomBar
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CamaraComponent(
                camaraData = CamaraData(
                    descripcion = R.string.vista_previa_camara
                )
            )
        }

        // BottomBar con degradado y botones
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(toolbarBackGround)
                .padding(vertical = 8.dp)
                .align(Alignment.BottomCenter)
        ) {
            CamaraButtonsComponent(
                camaraButtonData = CamaraButtonData(
                    hacerFoto = R.string.hacer_foto,
                    qr = R.string.qr
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CamaraScreenPreview() {
    MaterialTheme {
        CamaraScreen()
    }
}
