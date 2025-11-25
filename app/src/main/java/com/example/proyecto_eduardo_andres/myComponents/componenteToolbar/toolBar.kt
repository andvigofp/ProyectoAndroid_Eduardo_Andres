@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteToolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R

/**
 * @author Andrés
 * @see Componenente Toolbar
 * @param onBackClick: () -> Unit, // nuevo callback para el botón de volver
 * @param onHomeClick: () -> Unit, // Te llevará página de inicio
 * @param onCameraClick: () -> Unit, // Te llevará a la pantalla de la camara
 * @param onProfileClick: () -> Unit, Te llevara a la pantalla del perfil
 * @param onLogoutClick: () -> Unit: El toolbar tiene iconos, al clicar los iconos llevara al respetivo pantalla
 */
@Composable
fun toolBar(
    onBackClick: () -> Unit, // nuevo callback para el botón de volver
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    // Degradado de fondo (de azul oscuro a violeta suave)
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
            .fillMaxWidth()
            .height(56.dp)
            .background(toolbarBackGround),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // espacio entre izquierda y derecha
        ) {
            // Icono de volver a la izquierda
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.volver),
                    tint = Color(0xFFFFC107)
                )
            }

            // Íconos alineados a la derecha
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = onHomeClick) {
                    Icon(Icons.Default.Home, contentDescription = stringResource(R.string.inicio), tint = Color(0xFFFFC107))
                }
                IconButton(onClick = onCameraClick) {
                    Icon(Icons.Default.CameraAlt, contentDescription = stringResource(R.string.camara), tint = Color(0xFFFFC107))
                }
                IconButton(onClick = onProfileClick) {
                    Icon(Icons.Default.Person, contentDescription = stringResource(R.string.perfil), tint = Color(0xFFFFC107))
                }
                IconButton(onClick = onLogoutClick) {
                    Icon(Icons.Default.ExitToApp, contentDescription = stringResource(R.string.salir), tint = Color(0xFFFFC107))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToolBarPreview() {
    toolBar(
        onBackClick = {},
        onHomeClick = {},
        onCameraClick = {},
        onProfileClick = {},
        onLogoutClick = {}
    )
}
