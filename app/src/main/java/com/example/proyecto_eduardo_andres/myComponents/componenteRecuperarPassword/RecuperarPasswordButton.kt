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
    onRecuperarClick: () -> Unit,
    onCancelarClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppButton(
                data = ButtonData(
                    nombre = R.string.repetir_password, // O usar buttonText.recuperarPassword si tienes stringId
                    type = ButtonType.PRIMARY
                ),
                onClick = onRecuperarClick,
                modifier = Modifier.height(52.dp).weight(1f)
            )

            AppButton(
                data = ButtonData(
                    nombre = R.string.cancelar, // O usar buttonText.cancelar si tienes stringId
                    type = ButtonType.DANGER
                ),
                onClick = onCancelarClick,
                modifier = Modifier.height(52.dp).weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecuperarPasswordButtonPreview() {
    MaterialTheme {
        RecuperarPasswordButton(
            buttonText = RecuperarPasswordButtonText(
                cancelar = stringResource(R.string.cancelar),
                recuperarPassword = stringResource(R.string.repetir_password)
            ),
            onRecuperarClick = {},
            onCancelarClick = {}
        )
    }
}