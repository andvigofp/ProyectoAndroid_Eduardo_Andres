package com.example.proyecto_eduardo_andres.myComponents.componenteButtons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonData
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonType

/**
 * Componente genérico de botón que adapta su estilo según el tipo.
 *
 * Este botón se utiliza en la app para distintas acciones, por ejemplo:
 * - Alquilar o devolver películas en la pantalla de perfil o catálogo.
 *
 * @author Andrés
 *
 * @param data Información del botón, incluyendo:
 *  - nombre: ID del string que se mostrará en el botón.
 *  - type: Tipo de botón (PRIMARY, SECONDARY, DANGER, OUTLINE) que determina el estilo.
 *  - enabled: Indica si el botón está habilitado o deshabilitado (opcional, por defecto true).
 *
 * @param onClick Función que se ejecuta al pulsar el botón.
 * @param modifier Modifier opcional para ajustar el tamaño, forma o disposición del botón.
 *
 * @see PrimaryButton
 * @see SecondaryButton
 * @see DangerButton
 * @see OutlineButton
 */
@Composable
fun AppButton(
    data: ButtonData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonText = stringResource(id = data.nombre)

    when (data.type) {
        ButtonType.PRIMARY ->
            PrimaryButton(
                text = buttonText,
                onClick = onClick,
                enabled = data.enabled,
                modifier = modifier
            )

        ButtonType.SECONDARY ->
            SecondaryButton(
                text = buttonText,
                onClick = onClick,
                enabled = data.enabled,
                modifier = modifier
            )

        ButtonType.DANGER ->
            DangerButton(
                text = buttonText,
                onClick = onClick,
                enabled = data.enabled,
                modifier = modifier
            )

        ButtonType.OUTLINE ->
            OutlineButton(
                text = buttonText,
                onClick = onClick,
                enabled = data.enabled,
                modifier = modifier
            )
    }
}