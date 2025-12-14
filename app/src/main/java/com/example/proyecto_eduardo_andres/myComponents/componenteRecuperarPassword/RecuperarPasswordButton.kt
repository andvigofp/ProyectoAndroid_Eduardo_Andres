@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteRecuperarPassword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.viewData.recuperarPasswordData.RecuperarPasswordButtonText
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componenteButtons.AppButton
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonData
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonType

/**
 * @author Eduardo
 * @see Componenente Campo Botones
 * @param buttonText: Al clicar el botón Recuperar password, si todo va bien se cambiará la contraseña del usuario
 * @param buttonText: Al clicar el Cancelar, te llevará a la pantalla de Loging
 */
@Composable
fun RecuperarPasswordButton(
    buttonText: RecuperarPasswordButtonText,
    enabled: Boolean = true,
    onRecuperarClick: () -> Unit,
    onCancelarClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppButton(
            data = ButtonData(
                nombre = buttonText.recuperarPassword,
                type = ButtonType.PRIMARY,
                enabled = enabled
            ),
            onClick = onRecuperarClick,
            modifier = Modifier.height(52.dp).weight(1f)
        )

        AppButton(
            data = ButtonData(
                nombre = buttonText.cancelar,
                type = ButtonType.DANGER
            ),
            onClick = onCancelarClick,
            modifier = Modifier.height(52.dp).weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecuperarPasswordButtonPreview() {
    MaterialTheme {
        RecuperarPasswordButton(
            buttonText = RecuperarPasswordButtonText(
                cancelar = R.string.cancelar,
                recuperarPassword = R.string.recuperar
            ),
            enabled = true,
            onRecuperarClick = {},
            onCancelarClick = {}
        )
    }
}