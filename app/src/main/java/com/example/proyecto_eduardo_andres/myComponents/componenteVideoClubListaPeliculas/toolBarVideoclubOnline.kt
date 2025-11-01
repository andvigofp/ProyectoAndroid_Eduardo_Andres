package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun toolBarVideoClubOnline(
    drawerState: DrawerState,
    scope: CoroutineScope,
    onHomeClick: () -> Unit,
    onSearchClicK:() -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
// Fondo azul oscuro
    val toolbarBackground = MaterialTheme.colorScheme.primary
// Color amarillo oscuro para los iconos
    val iconColor = Color(0xFFFFB300) // Amarillo oscuro (tono ámbar intenso)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(toolbarBackground)
            .statusBarsPadding(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Ícono del Menú (izquierda) ---
            IconButton(
                onClick = { scope.launch { drawerState.open() } }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú",
                    tint = iconColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            // --- Íconos del lado derecho ---
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                IconButton(onClick = onHomeClick) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Inicio",
                        tint = iconColor
                    )
                }
                IconButton(onClick = onSearchClicK) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = iconColor
                    )
                }
                IconButton(onClick = onCameraClick) {
                    Icon(
                        Icons.Default.CameraAlt,
                        contentDescription = "Cámara",
                        tint = iconColor
                    )
                }
                IconButton(onClick = onProfileClick) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Perfil",
                        tint = iconColor
                    )
                }
                IconButton(onClick = onLogoutClick) {
                    Icon(
                        Icons.Default.ExitToApp,
                        contentDescription = "Salir",
                        tint = iconColor
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToolBarVideoClubOnlinePreview() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MaterialTheme {
        toolBarVideoClubOnline(
            drawerState = drawerState,
            scope = scope,
            onHomeClick = {},
            onSearchClicK = {},
            onCameraClick = {},
            onProfileClick = {},
            onLogoutClick = {}
        )
    }
}