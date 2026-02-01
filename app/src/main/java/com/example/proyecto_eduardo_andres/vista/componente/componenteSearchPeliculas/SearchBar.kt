@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteSearchPeliculas

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R

/**
 * @author Eduardo
 * @see Componenente Barra de busqueda
 * @param peliculas: Introduce el nombre de la pelicula
 */
@Composable
fun SearchBar(
    searchQuery: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        placeholder = { Text(stringResource( R.string.buscar_peliculas))},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}
