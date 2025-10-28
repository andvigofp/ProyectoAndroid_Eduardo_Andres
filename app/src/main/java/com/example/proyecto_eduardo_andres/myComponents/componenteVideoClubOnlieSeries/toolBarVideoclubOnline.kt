package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubOnlieSeries

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun toolBarVideoClubOnline(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
// Fondo con degradado
    val toolbarBackground = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.onPrimaryContainer,
            MaterialTheme.colorScheme.onPrimaryContainer),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(toolbarBackground),
        contentAlignment = Alignment.CenterStart
    ) {
        // Fila principal que ocupa todo el ancho
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween, // izquierda y derecha
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Flecha a la izquierda
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFFFFC107)
                )
            }

            // 📱 Íconos a la derecha
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = onHomeClick) {
                    Icon(Icons.Default.Home, contentDescription = "Inicio", tint = Color(0xFFFFC107))
                }
                IconButton(onClick = onCameraClick) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Cámara", tint = Color(0xFFFFC107))
                }
                IconButton(onClick = onProfileClick) {
                    Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color(0xFFFFC107))
                }
                IconButton(onClick = onLogoutClick) {
                    Icon(Icons.Default.ExitToApp, contentDescription = "Salir", tint = Color(0xFFFFC107))
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ToolBarPreview() {
    toolBarVideoClubOnline(
        onBackClick = {},
        onHomeClick = {},
        onCameraClick = {},
        onProfileClick = {},
        onLogoutClick = {}
    )
}