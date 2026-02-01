package com.example.proyecto_eduardo_andres.vista.componente.componenteCustomScreenPeliculasSeries

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Componente contenedor flexible sin usar Scaffold, que permite definir barras
 * superiores e inferiores opcionales y un contenido principal que ocupa el espacio restante.
 *
 * Útil cuando quieres un layout simple con topBar y/o bottomBar, sin la estructura completa de Scaffold.
 * @author Andrés
 * @param modifier Modifier opcional para aplicar al contenedor principal.
 * @param topBar Composable opcional que se muestra en la parte superior de la pantalla.
 * @param bottomBar Composable opcional que se muestra en la parte inferior de la pantalla.
 * @param content Composable principal que ocupa todo el espacio restante entre topBar y bottomBar.
 */
@Composable
fun CustomScreenWithoutScaffold(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        // TOP BAR (opcional)
        if (topBar != null) {
            topBar()
        }

        // CONTENIDO (ocupa todo el espacio restante)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            content()
        }

        // BOTTOM BAR (opcional)
        if (bottomBar != null) {
            bottomBar()
        }
    }
}