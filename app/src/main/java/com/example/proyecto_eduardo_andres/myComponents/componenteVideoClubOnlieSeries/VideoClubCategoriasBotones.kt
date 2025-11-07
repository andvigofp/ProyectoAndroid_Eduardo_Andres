package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubOnlieSeries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Eduardo
 * @see Componenente Botones
 * @param categorias: Al pulsar cualquier botón de llevará a lista de series de esa categoría correspondiente
 */
@Composable
fun VideoClubCategoriasBotones() {
    // Lista de categorías usando el data class
    val categorias = listOf(
        VideoClubOnlineCategoriasData("DRAMA", MaterialTheme.colorScheme.primary),
        VideoClubOnlineCategoriasData("ACCIÓN", MaterialTheme.colorScheme.secondary),
        VideoClubOnlineCategoriasData("TERROR", MaterialTheme.colorScheme.error),
        VideoClubOnlineCategoriasData("DIBUJOS", MaterialTheme.colorScheme.tertiary)
    )

    // Solo el LazyRow con los botones de categorías
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categorias.size) { index ->
            val (nombre, color) = categorias[index]

            Button(
                onClick = { /* En el futuro: navegar a la lista de esa categoría */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .height(55.dp)
                    .width(160.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shadow(4.dp)
            ) {
                Text(
                    text = nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VideoClubCategoriasBotonesPreview() {
    MaterialTheme {
        VideoClubCategoriasBotones()
    }
}
