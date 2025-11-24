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
import com.example.proyecto_eduardo_andres.viewData.RecuperarPasswordData.RecuperarPasswordButtonText
import com.example.proyecto_eduardo_andres.R

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
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onRecuperarClick,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp) // altura consistente si quieres
            ) {
                Text(
                    text = buttonText.recuperarPassword,
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = onCancelarClick,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(
                    text = buttonText.cancelar,
                    fontSize = 14.sp
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RecuperarPasswordButtonPreview() {
    RecuperarPasswordButton(
        buttonText = RecuperarPasswordButtonText(
            cancelar = stringResource(R.string.cancelar),
            recuperarPassword = stringResource(R.string.repetir_password)
        ),
        onRecuperarClick = {},
        onCancelarClick = {}
    )
}