package com.example.proyecto_eduardo_andres.myComponents.componenteButtons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Botón de acción principal con color de error (rojo) para acciones peligrosas o críticas.
 * @author Andrés
 * @param text Texto que se mostrará en el botón.
 * @param onClick Función que se ejecuta al pulsar el botón.
 * @param enabled Indica si el botón está habilitado (true por defecto).
 * @param modifier Modifier opcional para ajustar tamaño, forma o disposición.
 */
@Composable
fun DangerButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text)
    }
}
