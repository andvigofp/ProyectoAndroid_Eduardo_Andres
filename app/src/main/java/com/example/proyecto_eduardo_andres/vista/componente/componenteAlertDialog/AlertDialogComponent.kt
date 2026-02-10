// AlertDialogComponent.kt
package com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Componente de AlertDialog reutilizable para toda la aplicación
 * @author Andrés
 * @param showDialog Booleano que controla si mostrar el diálogo
 * @param onDismissRequest Función llamada cuando se descarta el diálogo
 * @param title Título del diálogo
 * @param message Mensaje del diálogo
 * @param confirmButtonText Texto del botón de confirmación (por defecto "OK")
 * @param dismissButtonText Texto del botón de cancelación (opcional, si es null no se muestra)
 * @param onConfirmClick Función llamada al confirmar
 * @param onDismissClick Función llamada al cancelar (si dismissButtonText no es null)
 * @param isDestructive Indica si es un diálogo de acción destructiva (rojo)
 * @param shape Forma del diálogo
 */
@Composable
fun AlertDialogComponent(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    title: String,
    message: String,
    confirmButtonText: String? = null,
    dismissButtonText: String? = null,
    onConfirmClick: () -> Unit = {},
    onDismissClick: () -> Unit = {},
    isDestructive: Boolean = false,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    if (showDialog) {
        val colors = MaterialTheme.colorScheme
        val typography = MaterialTheme.typography


        var confirmComponent: @androidx.compose.runtime.Composable (() -> Unit)? = {}

        if(confirmButtonText != null)
            confirmComponent = @androidx.compose.runtime.Composable {
                Button(
                    onClick = {
                        onConfirmClick()
                        onDismissRequest()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDestructive) colors.error else colors.primary
                    )
                ) {
                    Text(
                        text = confirmButtonText,
                        style = typography.labelLarge,
                        color = colors.onPrimary
                    )
                }
            }
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(
                    text = title,
                    style = typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = colors.onSurface
                )
            },
            text = {
                Text(
                    text = message,
                    style = typography.bodyMedium,
                    color = colors.onSurfaceVariant
                )
            },
            confirmButton = confirmComponent as @Composable (() -> Unit),
            dismissButton = if (dismissButtonText != null) {
                {
                    TextButton(
                        onClick = {
                            onDismissClick()
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = dismissButtonText,
                            style = typography.labelLarge,
                            color = colors.onSurfaceVariant
                        )
                    }
                }
            } else {
                null
            },
            containerColor = colors.surface,
            shape = shape,
            tonalElevation = 8.dp
        )
    }
}

/**
 * Versión simplificada para diálogos de información
 */
@Composable
fun InfoDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    title: String,
    message: String,

) {
    AlertDialogComponent(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
        title = title,
        message = message,

        dismissButtonText = null
    )
}

/**
 * Diálogo de confirmación (Sí/No)
 */
@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    title: String,
    message: String,
    confirmButtonText: String = "Confirmar",
    dismissButtonText: String = "Cancelar",
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit = {},
    isDestructive: Boolean = false
) {
    AlertDialogComponent(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
        title = title,
        message = message,
        confirmButtonText = confirmButtonText,
        dismissButtonText = dismissButtonText,
        onConfirmClick = onConfirmClick,
        onDismissClick = onDismissClick,
        isDestructive = isDestructive
    )
}

/**
 * Diálogo de éxito
 */
@Composable
fun SuccessDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    title: String = "¡Éxito!",
    message: String,
    confirmButtonText: String = "Continuar"
) {
    AlertDialogComponent(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
        title = title,
        message = message,
        confirmButtonText = confirmButtonText
    )
}

/**
 * Diálogo de error
 */
@Composable
fun ErrorDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    title: String = "Error",
    message: String,
    confirmButtonText: String = "Aceptar"
) {
    AlertDialogComponent(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
        title = title,
        message = message,
        confirmButtonText = confirmButtonText
    )
}

@Preview
@Composable
fun AlertDialogPreview() {
    AlertDialogComponent(
        showDialog = true,
        onDismissRequest = {},
        title = "Título del Diálogo",
        message = "Este es un mensaje de ejemplo para mostrar cómo se ve el AlertDialogComponent en la aplicación.",
        onConfirmClick = { /* Acción al confirmar */ },
        onDismissClick = { /* Acción al cancelar */ },
        isDestructive = false
    )
}