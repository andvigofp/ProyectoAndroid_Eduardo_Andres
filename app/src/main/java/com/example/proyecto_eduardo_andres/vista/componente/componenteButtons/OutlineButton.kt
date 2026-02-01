package com.example.proyecto_eduardo_andres.vista.componente.componenteButtons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * Botón con borde contorneado, sin fondo sólido, para acciones secundarias o neutras.
 * @author Andrés
 * @param text Texto que se mostrará en el botón.
 * @param onClick Función que se ejecuta al pulsar el botón.
 * @param enabled Indica si el botón está habilitado (true por defecto).
 * @param modifier Modifier opcional para ajustar tamaño, forma o disposición.
 */
@Composable
fun OutlineButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text)
    }
}
