@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componentePerfilUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewData.PerfilUsuarioData.PerfilUsuarioButtonTextsData

/**
 * @author Eduardo
 * @see Componenente Campo Botones
 * @param perfilUsuarioButtonTextsData: Al clicar botón Modicar, modificará los datos del usuario
 */
@Composable
fun PerfilUsuarioButtons(
    perfilUsuarioButtonTextsData: PerfilUsuarioButtonTextsData,
    onModificarUsuario: () -> Unit,
) {
    // Si el data class viene vacío, damos los textos por defecto aquí
    val modificarUsuarioText =
        if (perfilUsuarioButtonTextsData.modificar.isNotEmpty()) perfilUsuarioButtonTextsData.modificar else stringResource(R.string.modificar_usuario)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onModificarUsuario,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ),
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
        ) {
            Text(text = modificarUsuarioText, fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilUsuarioButtonPreview() {
    MaterialTheme {
        Surface(color = Color(0xFFF5F5F5)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                PerfilUsuarioButtons (
                    perfilUsuarioButtonTextsData = PerfilUsuarioButtonTextsData(),
                    onModificarUsuario = {},
                )
            }
        }
    }
}