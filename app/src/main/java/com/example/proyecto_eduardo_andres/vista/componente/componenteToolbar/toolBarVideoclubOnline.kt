@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteToolbar

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.colorAmarillo
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.proyecto_eduardo_andres.R

/**
 * @author Andrés
 * @see Componenente Toolbar
 * @param drawerState: () -> Unit, // El icono Menú(burger), que tiene dos Menus Items
 * @param scope: es el Ambito de la corrutina, Es decir, determina el ciclo de vida
 * @param onHomeClick: () -> Unit, // Te llevará página de inicio
 * @param onCameraClick: () -> Unit, // Te llevará a la pantalla de la camara
 * @param onProfileClick: () -> Unit, Te llevara a la pantalla del perfil
 * @param onLogoutClick: () -> Unit: El toolbar tiene iconos, al clicar los iconos llevara al respetivo pantalla
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun toolBarVideoClubOnline(
    drawerState: DrawerState,
    scope: CoroutineScope,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
// Fondo azul oscuro
    val toolbarBackGround = Brush.linearGradient(
        colors = listOf(
            colorVioleta,
            colorAzulOscurso
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )
// Color amarillo oscuro para los iconos
    val iconColor = colorAmarillo // Amarillo oscuro (tono ámbar intenso)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(toolbarBackGround)
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
                    contentDescription = stringResource(R.string.menu_option),
                    tint = iconColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            // --- Íconos del lado derecho ---
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                IconButton(onClick = onHomeClick) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = stringResource(R.string.inicio),
                        tint = iconColor
                    )
                }
                IconButton(onClick = onSearchClick) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(R.string.buscar),
                        tint = iconColor
                    )
                }
                IconButton(onClick = onCameraClick) {
                    Icon(
                        Icons.Default.CameraAlt,
                        contentDescription = stringResource(R.string.camara),
                        tint = iconColor
                    )
                }
                IconButton(onClick = onProfileClick) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = stringResource(R.string.perfil),
                        tint = iconColor
                    )
                }
                IconButton(onClick = onLogoutClick) {
                    Icon(
                        Icons.Default.ExitToApp,
                        contentDescription = stringResource(R.string.salir),
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
            onSearchClick = {},
            onCameraClick = {},
            onProfileClick = {},
            onLogoutClick = {}
        )
    }
}