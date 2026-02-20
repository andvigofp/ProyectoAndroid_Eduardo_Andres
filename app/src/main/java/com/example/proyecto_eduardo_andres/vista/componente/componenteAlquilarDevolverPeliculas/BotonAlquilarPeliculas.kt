@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteAlquilarDevolverPeliculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.vista.componente.componenteButtons.AppButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author Eduardo
 * @see Componenente Bottóns para Alquilar y Devolver Peliculas
 * @param botonAlquilar: Al cliclar el botón Alquilar llama la funcion Alquilar
 * @param botonAlquilar: Al ciclar Botón Devolver te aparecerá una pantalla emergente
 */
@Composable
fun BotonAlquilarPeliculas(
    botonAlquilar: ButtonData,
    botonDevolver: ButtonData,
    onAlquilarClick: () -> Unit = {},
    onDevolverClick: () -> Unit = {},
    isAlquilarButtonEnabled: Boolean,
    isDevolverButtonEnabled: Boolean
) {
    var showDialog by remember { mutableStateOf(false) }

    val fechaActual = remember {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.format(Date())
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // Botón Alquilar (usa tu botón atómico)
        AppButton(
            data = botonAlquilar,
            onClick = onAlquilarClick,
            modifier = Modifier
                .weight(1f)
                .then(Modifier.alpha(if (isAlquilarButtonEnabled) 1f else 0.5f)) // Hacer que se vea deshabilitado
        )

        // Botón Devolver (usa tu botón atómico)
        AppButton(
            data = botonDevolver,
            onClick = {
                if (isDevolverButtonEnabled) {
                    onDevolverClick()
                    showDialog = true
                }
            },
            modifier = Modifier
                .weight(1f)
                .alpha(if (isDevolverButtonEnabled) 1f else 0.5f)
        )
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = MaterialTheme.colorScheme.primary, // Fondo azul como en AlquilarDevolverDialog
            title = {
                Text(
                    text = stringResource(R.string.devolucion_completada),
                    color = Color.White // Asegura que el título sea blanco
                )
            },
            text = {
                Text("${stringResource(R.string.devolver_pelicula)} $fechaActual", color = Color.White)
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(R.string.aceptar), color = Color.White) // Color blanco para el botón
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BotonAlquilarPeliculasPreview() {
    MaterialTheme {
        // Aquí definimos los valores de enabled para los botones
        BotonAlquilarPeliculas(
            botonAlquilar = ButtonData(
                nombre = R.string.alquilar,
                type = ButtonType.PRIMARY
            ),
            botonDevolver = ButtonData(
                nombre = R.string.devolver,
                type = ButtonType.SECONDARY
            ),
            isAlquilarButtonEnabled = true, // El botón de alquilar está habilitado
            isDevolverButtonEnabled = false  // El botón de devolver está deshabilitado
        )
    }
}
