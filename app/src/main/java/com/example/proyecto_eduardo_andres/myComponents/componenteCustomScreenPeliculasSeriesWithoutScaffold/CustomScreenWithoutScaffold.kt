package com.example.proyecto_eduardo_andres.myComponents.componenteCustomScreenPeliculasSeriesWithoutScaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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