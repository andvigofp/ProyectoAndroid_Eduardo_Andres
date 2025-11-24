@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteCamara

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.viewData.CamaraData.CamaraButtonData
import com.example.proyecto_eduardo_andres.R

/**
 * @author Andrés
 * @see Componenente Buttón Canara
 * @param camaraButtonData: Al clicar el botón te hace una foto, guarda la foto
 * @param camaraButtonData: Al clicar el botón QR te lleva a la pagína de QR
 */
@Composable
fun CamaraButtonsComponent(camaraButtonData: CamaraButtonData) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Botón Hacer Foto (izquierda)
        Button(
            onClick = { /* TODO: acción hacer foto */ },
            colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(camaraButtonData.hacerFoto),
                style = typography.labelLarge.copy(color = Color.White)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Botón QR (derecha)
        Button(
            onClick = { /* TODO: acción abrir QR */ },
            colors = ButtonDefaults.buttonColors(containerColor = colors.secondary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource( camaraButtonData.qr),
                style = typography.labelLarge.copy(color = Color.White)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CamaraButtonsComponentPreview() {
    val botonesDemo = CamaraButtonData(
        hacerFoto = R.string.hacer_foto,
        qr = R.string.qr
    )

    MaterialTheme {
        CamaraButtonsComponent(camaraButtonData = botonesDemo)
    }
}
