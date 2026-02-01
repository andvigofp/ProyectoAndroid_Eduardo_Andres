package com.example.proyecto_eduardo_andres.vista.pagina

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AlquilarDevolverDialog(
    isAlquiler: Boolean,
    fechaAlquiler: Date?,
    fechaDevolucion: Date?,
    onConfirmClick: () -> Unit
) {
    // Formateador de fecha
    val dateFormat = SimpleDateFormat(stringResource(R.string.fecha), Locale.getDefault())
    val fechaAlquilerFormatted = fechaAlquiler?.let { dateFormat.format(it) }
    val fechaDevolucionFormatted = fechaDevolucion?.let { dateFormat.format(it) }

    // Multa
    val fechaActual = Date()
    val esMulta = fechaDevolucion != null && fechaActual.after(fechaDevolucion)

    // Texto del estado de la película
    val estadoPelicula = stringResource(
        id = R.string.estado_pelicula,
        if (isAlquiler) "Película Alquilada" else "Película Devuelta"
    )

    // Textos para fechas y multa
    val fechaAlquilerText = stringResource(id = R.string.fecha_alquiler, fechaAlquilerFormatted ?: "")
    val fechaDevolucionText = stringResource(id = R.string.fecha_devolucion, fechaDevolucionFormatted ?: "")
    val multaText = stringResource(id = R.string.multa_devolucion_tarde)
    val aceptarText = stringResource(id = R.string.boton_aceptar)

    AlertDialog(
        onDismissRequest = onConfirmClick,
        title = { Text(text = estadoPelicula, style = MaterialTheme.typography.bodyLarge) },
        text = {
            Column {
                Text(fechaAlquilerText)
                if (!isAlquiler) {
                    Text(fechaDevolucionText)
                    if (esMulta) Text(multaText, color = MaterialTheme.colorScheme.error)
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(aceptarText)
            }
        }
    )
}

// Preview mostrando alquiler y devolución con multa
@Preview(showBackground = true)
@Composable
fun AlquilarDevolverDialogPreview() {
    val fechaAlquiler = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -10) }.time
    val fechaDevolucion = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -3) }.time

    Column {
        // Diálogo de alquiler
        AlquilarDevolverDialog(
            isAlquiler = true,
            fechaAlquiler = fechaAlquiler,
            fechaDevolucion = null,
            onConfirmClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Diálogo de devolución (con multa)
        AlquilarDevolverDialog(
            isAlquiler = false,
            fechaAlquiler = fechaAlquiler,
            fechaDevolucion = fechaDevolucion,
            onConfirmClick = {}
        )
    }
}


