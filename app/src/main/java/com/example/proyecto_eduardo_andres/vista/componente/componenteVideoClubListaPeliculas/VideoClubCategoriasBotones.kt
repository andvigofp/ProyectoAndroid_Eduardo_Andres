@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteVideoClubListaPeliculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineCategoriasDto
import com.example.proyecto_eduardo_andres.R

/**
 * @author Andres
 * @see Componenente Botones
 * @param categorias: Al pulsar cualquier botón de llevará a lista de peliculas de esa categoría correspondiente
 */
@Composable
fun VideoClubCategoriasBotones(
    onCategoriaClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val categorias = listOf(
        VideoClubOnlineCategoriasDto(R.string.drama, MaterialTheme.colorScheme.primary),
        VideoClubOnlineCategoriasDto(R.string.accion, MaterialTheme.colorScheme.secondary),
        VideoClubOnlineCategoriasDto(R.string.terror, MaterialTheme.colorScheme.error),
        VideoClubOnlineCategoriasDto(R.string.dibujos, MaterialTheme.colorScheme.tertiary)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(categorias) { (nombre, color) ->
            Button(
                onClick = { onCategoriaClick(nombre) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .height(55.dp)
                    .width(160.dp)
            ) {
                Text(
                    text = stringResource(nombre),
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
        VideoClubCategoriasBotones(
            onCategoriaClick = {  }
        )
    }
}
