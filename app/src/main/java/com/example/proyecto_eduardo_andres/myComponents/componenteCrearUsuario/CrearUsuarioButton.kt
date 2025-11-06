package com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author Eduardo
 * @see Componenente Botones Crear usuario
 * @param  crearUsuarioButtonText: Al clicar el botón crear Usuario, te saldra una pantalla emergente te pondra el usuario que se a creado
 * @param  crearUsuarioButtonText: El botón Cancelar, se canlela crear el usuarío y te lleva de vuelva a pantalla de Logíng
 */
@Composable
fun CrearUsuarioButton(
    crearUsuarioButtonText: CrearUsuarioButtonTextData,
    onCrearUsuarioClick: () -> Unit,
    onCancelarClick: () -> Unit
){

    // Si el data class viene vacío, damos los textos por defecto aquí
    val crearUsarioText = if (crearUsuarioButtonText.crearUsuario.isNotEmpty()) crearUsuarioButtonText.crearUsuario else "CREAR USUARIO"
    val cancelarText = if (crearUsuarioButtonText.crearUsuario.isNotEmpty()) crearUsuarioButtonText.cancelar else "CANCELAR"

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onCrearUsuarioClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ),
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
        ) {
            Text(text = crearUsarioText, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = onCancelarClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White
            ),
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
        ) {
            Text(
                text = cancelarText,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrearUsuarioButtonPreview() {
    MaterialTheme {
        Surface(color = Color(0xFFF5F5F5)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CrearUsuarioButton(
                    crearUsuarioButtonText = CrearUsuarioButtonTextData(),
                    onCrearUsuarioClick = {},
                    onCancelarClick = {}
                )
            }
        }
    }
}