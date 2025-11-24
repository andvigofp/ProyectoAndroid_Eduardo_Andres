@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewData.AlquilerDevolverSeriesData.BotonAlquilarDevolverData
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
    botonAlquilar: BotonAlquilarDevolverData,
    botonDevolver: BotonAlquilarDevolverData,
    onAlquilarClick: () -> Unit = {},
    onDevolverClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    var showDialog by remember { mutableStateOf(false) }

    // Obtener la fecha actual (esto está correcto como tú quieres)
    val fechaActual = remember {
        val dateFormat = SimpleDateFormat(R.string.fecha.toString(), Locale.getDefault())
        dateFormat.format(Date())
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // Botón Alquilar
        Button(
            onClick = onAlquilarClick,
            colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(botonAlquilar.nombreBoton),
                style = typography.labelLarge.copy(color = colors.onPrimary)
            )
        }

        // Botón Devolver
        Button(
            onClick = {
                onDevolverClick()
                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = colors.tertiary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(botonDevolver.nombreBoton),
                style = typography.labelLarge.copy(color = colors.onPrimary)
            )
        }
    }

    // Diálogo emergente
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.devolucion_completada)) },
            text = {
                Text("${stringResource(R.string.devolver_pelicula)} $fechaActual")
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(R.string.aceptar))
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BotonAlquilarPeliculasPreview() {
    MaterialTheme {
        BotonAlquilarPeliculas(
            botonAlquilar = BotonAlquilarDevolverData(nombreBoton = R.string.alquilar),
            botonDevolver = BotonAlquilarDevolverData(nombreBoton = R.string.devolver)
        )
    }
}