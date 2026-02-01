@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componentePerfilUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.PerfilUsuarioButtonTextsDto
import com.example.proyecto_eduardo_andres.vista.componente.componenteButtons.PrimaryButton

/**
 * @author Eduardo
 * @see Componenente Campo Botones
 * @param perfilUsuarioButtonTextsData: Al clicar botón Modicar, modificará los datos del usuario
 */
@Composable
fun PerfilUsuarioButtons(
    perfilUsuarioButtonTextsData: PerfilUsuarioButtonTextsDto,
    onModificarUsuario: () -> Unit
) {
    // Texto del botón con valor por defecto si viene vacío
    val modificarUsuarioText =
        if (perfilUsuarioButtonTextsData.modificar.isNotEmpty())
            perfilUsuarioButtonTextsData.modificar
        else
            stringResource(R.string.modificar_usuario)

    // Contenedor centrado
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PrimaryButton(
            text = modificarUsuarioText,
            onClick = onModificarUsuario,
            enabled = true,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth(0.9f) // 90% del ancho
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilUsuarioButtonPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceBright) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                PerfilUsuarioButtons(
                    perfilUsuarioButtonTextsData = PerfilUsuarioButtonTextsDto(),
                    onModificarUsuario = {}
                )
            }
        }
    }
}