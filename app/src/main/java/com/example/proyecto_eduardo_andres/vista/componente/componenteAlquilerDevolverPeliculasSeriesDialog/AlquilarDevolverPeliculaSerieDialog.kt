package com.example.proyecto_eduardo_andres.vista.componente.componenteAlquilerDevolverPeliculasSeriesDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * @author Andrés
 *
 * Composable que muestra un diálogo informativo
 * para indicar el resultado de una acción de alquiler
 * o devolución de película/serie.
 *
 * Este componente:
 * - Muestra el estado de la operación (Alquilada / Devuelta).
 * - Formatea fechas según configuración regional.
 * - Indica si existe multa por devolución tardía.
 * - Utiliza Material3 AlertDialog.
 *
 * Sigue el patrón declarativo de Jetpack Compose:
 * - La UI depende únicamente de los parámetros recibidos.
 * - No contiene lógica de negocio.
 * - Se recompone automáticamente cuando cambian los valores.
 *
 * Diseño:
 * - Usa MaterialTheme para tipografías y colores.
 * - Personaliza el fondo del diálogo.
 * - Usa colores adaptados al estado (error si hay multa).
 *
 * @param isAlquiler Indica si la acción es alquiler (true)
 * o devolución (false).
 * @param fechaAlquiler Fecha en la que se realizó el alquiler.
 * @param fechaDevolucion Fecha en la que se devolvió el contenido.
 * @param fechaLimiteDevolucion Fecha límite para devolver sin multa.
 * @param esMulta Indica si la devolución genera multa.
 * @param onConfirmClick Callback ejecutado al cerrar el diálogo.
 *
 * @see AlertDialog
 * @see MaterialTheme
 * @see stringResource
 * @see Date
 * @see SimpleDateFormat
 */
@Composable
fun AlquilarDevolverDialog(
    isAlquiler: Boolean,
    fechaAlquiler: Date?,
    fechaDevolucion: Date?,
    fechaLimiteDevolucion: Date?,
    esMulta: Boolean,
    onConfirmClick: () -> Unit
) {
    val dateFormat = SimpleDateFormat(stringResource(R.string.fecha), Locale.getDefault())
    val fechaAlquilerFormatted = fechaAlquiler?.let { dateFormat.format(it) }
    val fechaDevolucionFormatted = fechaDevolucion?.let { dateFormat.format(it) }
    val fechaLimiteFormatted = fechaLimiteDevolucion?.let { dateFormat.format(it) }

    val estadoPelicula = stringResource(
        id = R.string.estado_pelicula,
        if (isAlquiler) "Película Alquilada" else "Película Devuelta"
    )

    val fechaAlquilerText =
        stringResource(id = R.string.fecha_alquiler, fechaAlquilerFormatted ?: "")
    val fechaDevolucionText =
        stringResource(id = R.string.fecha_devolucion, fechaDevolucionFormatted ?: "")
    val fechaLimiteText =
        stringResource(id = R.string.fecha_limite_devolucion, fechaLimiteFormatted ?: "")
    val multaText = stringResource(id = R.string.multa_devolucion_tarde)
    val devueltoATiempoText = stringResource(id = R.string.devuelto_a_tiempo)
    val aceptarText = stringResource(id = R.string.boton_aceptar)

    AlertDialog(
        onDismissRequest = onConfirmClick,
        containerColor = MaterialTheme.colorScheme.primary, // Fondo azul menos saturado
        title = {
            Text(
                text = estadoPelicula,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White // Asegurarse de que el título tenga color blanco
            )
        },
        text = {
            Column {
                Text(
                    fechaAlquilerText,
                    color = Color.White // Cambiar color de texto a blanco
                )

                if (!isAlquiler) {
                    Text(
                        fechaDevolucionText,
                        color = Color.White // Cambiar color de texto a blanco
                    )
                    Text(
                        fechaLimiteText,
                        color = Color.White // Cambiar color de texto a blanco
                    )

                    if (esMulta) {
                        Text(
                            multaText,
                            color = MaterialTheme.colorScheme.error
                        )
                    } else {
                        Text(
                            devueltoATiempoText,
                            color = Color.White // Cambiar color de texto a blanco
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(aceptarText, color = Color.White) // Color blanco para el botón
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

    // Fecha límite de devolución = fechaAlquiler + 7 días
    val fechaLimiteDevolucion = Calendar.getInstance().apply {
        time = fechaAlquiler
        add(Calendar.DAY_OF_MONTH, 7)
    }.time

    // Multa si fechaDevolucion es después de fechaLimiteDevolucion
    val esMulta = fechaDevolucion.after(fechaLimiteDevolucion)

    Column {
        // Diálogo de alquiler
        AlquilarDevolverDialog(
            isAlquiler = true,
            fechaAlquiler = fechaAlquiler,
            fechaDevolucion = null,
            onConfirmClick = {},
            fechaLimiteDevolucion = fechaLimiteDevolucion,
            esMulta = false // al alquilar no hay multa
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Diálogo de devolución (con multa si corresponde)
        AlquilarDevolverDialog(
            isAlquiler = false,
            fechaAlquiler = fechaAlquiler,
            fechaDevolucion = fechaDevolucion,
            onConfirmClick = {},
            fechaLimiteDevolucion = fechaLimiteDevolucion,
            esMulta = esMulta
        )
    }
}