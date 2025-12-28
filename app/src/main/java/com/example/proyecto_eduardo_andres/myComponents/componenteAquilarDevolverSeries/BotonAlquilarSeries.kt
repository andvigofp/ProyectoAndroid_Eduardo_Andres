@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componenteButtons.AppButton
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonData
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author Andrés
 * @see Componenente Bottóns para Alquilar y Devolver Peliculas
 * @param botonAlquilar: Al cliclar el botón Alquilar llama la funcion Alquilar
 * @param botonAlquilar: Al ciclar Botón Devolver te aparecerá una pantalla emergente, con la fecha actual
 */
@Composable
fun BotonAlquilarSeries(
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
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // ---- Botón Alquilar ----
        AppButton(
            data = botonAlquilar,
            onClick = onAlquilarClick,
            modifier = Modifier.weight(1f)
        )

        // ---- Botón Devolver ----
        AppButton(
            data = botonDevolver,
            onClick = {
                onDevolverClick()
                showDialog = true
            },
            modifier = Modifier.weight(1f)
        )
    }

    // ---- Diálogo ----
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.devolucion_completada)) },
            text = {
                Text("${stringResource(R.string.devolver_serie)} $fechaActual")
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
fun BotonAlquilarSeriesPreview() {
    MaterialTheme {
        BotonAlquilarSeries(
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