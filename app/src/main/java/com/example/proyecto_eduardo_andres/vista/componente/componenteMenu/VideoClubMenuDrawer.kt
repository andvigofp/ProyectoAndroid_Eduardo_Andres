@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToQueue
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.MenuItemDto

/**
 * @author Andrés
 * @see Componenente Campo Menu
 * @param  drawerState: Este componente va tener dos Menus Items uno para peliculas que al clicar te lleva a página de peliculas
 * La de al clicar te lleva a página de series
 */
@Composable
fun VideoClubMenuDrawer(
    currentScreen: DrawerScreen,
    drawerState: DrawerState,
    scope: CoroutineScope,
    onPeliculasClick: () -> Unit,
    onSeriesClick: () -> Unit,
    onInfoClick: () -> Unit
) {

    val colors = MaterialTheme.colorScheme

    val menuItems = mutableListOf<MenuItemDto>()

    if (currentScreen != DrawerScreen.PELICULAS) {
        menuItems.add(
            MenuItemDto(
                title = stringResource(R.string.peliculas),
                icon = Icons.Default.Movie,
                onClick = onPeliculasClick
            )
        )
    }

    if (currentScreen != DrawerScreen.SERIES) {
        menuItems.add(
            MenuItemDto(
                title = stringResource(R.string.series),
                icon = Icons.Default.AddToQueue,
                onClick = onSeriesClick
            )
        )
    }

    menuItems.add(
        MenuItemDto(
            title = stringResource(R.string.info),
            icon = Icons.Default.Info,
            onClick = onInfoClick
        )
    )

    ModalDrawerSheet(
        modifier = Modifier
            .width(270.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colors.primaryContainer,
                        colors.secondaryContainer
                    )
                )
            ),
        drawerContainerColor = Color.Transparent,
        drawerContentColor = colors.onPrimaryContainer
    ) {

        Spacer(modifier = Modifier.height(36.dp))

        // --- TÍTULO ---
        Text(
            text = stringResource(R.string.videoclub_online),
            color = colors.onPrimaryContainer,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- ITEMS ---
        menuItems.forEach { item ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        scope.launch { drawerState.close() }
                        item.onClick()
                    }
                    .background(
                        colors.surface.copy(alpha = 0.25f)
                    )
                    .padding(vertical = 14.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = colors.onPrimaryContainer
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = item.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubMenuDrawerPreview() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MaterialTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                VideoClubMenuDrawer(
                    drawerState = drawerState,
                    scope = scope,
                    onPeliculasClick = {},
                    onSeriesClick = {},
                    currentScreen = DrawerScreen.SERIES,
                    onInfoClick = {}
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(R.string.videoClub)) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = stringResource(R.string.menu),
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        stringResource(R.string.pulsa_icono_del_menu),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}