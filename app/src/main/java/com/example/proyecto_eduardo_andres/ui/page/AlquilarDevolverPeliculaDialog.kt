package com.example.proyecto_eduardo_andres.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.proyecto_eduardo_andres.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AlquilarDevolverDialog(
    isAlquiler: Boolean,
    fechaAlquiler: Date?,
    fechaDevolucion: Date?,
    onConfirmClick: () -> Unit
) {
    // Crear el formateador de fecha
    val dateFormat = SimpleDateFormat(stringResource(R.string.fecha), Locale.getDefault())

    // Formatear las fechas a cadenas (String)
    val fechaAlquilerFormatted = fechaAlquiler?.let { dateFormat.format(it) }
    val fechaDevolucionFormatted = fechaDevolucion?.let { dateFormat.format(it) }

    // Comparar fechas para ver si se aplica multa
    val fechaActual = Date()
    val esMulta = fechaDevolucion != null && fechaActual.after(fechaDevolucion)

    // Obtener el estado de la película dinámicamente
    val estadoPelicula = if (isAlquiler) {
        stringResource(id = R.string.estado_pelicula, "Película Alquilada")
    } else {
        stringResource(id = R.string.estado_pelicula, "Película Devuelta")
    }

    // Usar stringResource con el placeholder de fecha de alquiler
    val fechaAlquilerText = stringResource(id = R.string.fecha_alquiler, fechaAlquilerFormatted ?: "")

    // Usar stringResource con el placeholder de fecha de devolución
    val fechaDevolucionText = stringResource(id = R.string.fecha_devolucion, fechaDevolucionFormatted ?: "")

    // Usar stringResource para la multa
    val multaText = stringResource(id = R.string.multa_devolucion_tarde)

    // Usar stringResource para el botón Aceptar
    val aceptarText = stringResource(id = R.string.boton_aceptar)

    AlertDialog(
        onDismissRequest = { /* Cerrar el diálogo */ },
        title = {
            Text(
                text = estadoPelicula,  // Usamos el texto dinámico del estado de la película
                style = MaterialTheme.typography.bodyLarge
            )
        },
        text = {
            Column {
                // Mostrar fecha de alquiler usando el stringResource
                Text(fechaAlquilerText)  // Aquí mostramos la fecha de alquiler
                if (!isAlquiler) {
                    // Mostrar fecha de devolución usando el stringResource
                    Text(fechaDevolucionText)  // Aquí mostramos la fecha de devolución
                    if (esMulta) {
                        Text(
                            multaText,  // Usamos el stringResource con el texto de multa
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(aceptarText)  // Usamos el stringResource con el texto "Aceptar"
            }
        }
    )
}