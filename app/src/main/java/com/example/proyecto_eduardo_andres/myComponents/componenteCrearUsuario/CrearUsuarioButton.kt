@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.myComponents.componenteButtons.AppButton
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonData
import com.example.proyecto_eduardo_andres.viewData.buttonsData.ButtonType

/**
 * @author Eduardo
 * @see Componenente Botones Crear usuario
 * @param  crearUsuarioButtonText: Al clicar el botón crear Usuario, te saldra una pantalla emergente te pondra el usuario que se a creado
 * @param  crearUsuarioButtonText: El botón Cancelar, se canlela crear el usuario y te lleva de vuelta a pantalla de Logíng
 */
@Composable
fun CrearUsuarioButton(
    crearButton: ButtonData,
    cancelarButton: ButtonData,
    onCrearClick: () -> Unit,
    onCancelarClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón Crear Usuario
        AppButton(
            data = crearButton,
            onClick = onCrearClick,
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Botón Cancelar
        AppButton(
            data = cancelarButton,
            onClick = onCancelarClick,
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrearUsuarioButtonPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CrearUsuarioButton(
                    crearButton = ButtonData(
                        nombre = R.string.crear_usuario,
                        type = ButtonType.PRIMARY
                    ),
                    cancelarButton = ButtonData(
                        nombre = R.string.cancelar,
                        type = ButtonType.SECONDARY
                    ),
                    onCrearClick = { /* TODO: crear usuario */ },
                    onCancelarClick = { /* TODO: cancelar */ }
                )
            }
        }
    }
}