@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteCamara

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.ButtonData
import com.example.proyecto_eduardo_andres.modelo.ButtonType
import com.example.proyecto_eduardo_andres.vista.componente.componenteButtons.AppButton

/**
 * @author Andrés
 * @see Componenente Buttón Canara
 * @param camaraButtonData: Al clicar el botón te hace una foto, guarda la foto
 * @param camaraButtonData: Al clicar el botón QR te lleva a la pagína de QR
 */
@Composable
fun CamaraButtonsComponent(
    hacerFotoButton: ButtonData,
    qrButton: ButtonData,
    onHacerFotoClick: () -> Unit = {},
    onQrClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Botón Hacer Foto
        AppButton(
            data = hacerFotoButton,
            onClick = onHacerFotoClick,
            modifier = Modifier.weight(1f)
        )

        // Botón QR
        AppButton(
            data = qrButton,
            onClick = onQrClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CamaraButtonsComponentPreview() {
    MaterialTheme {
        CamaraButtonsComponent(
            hacerFotoButton = ButtonData(
                nombre = R.string.hacer_foto,
                type = ButtonType.PRIMARY
            ),
            qrButton = ButtonData(
                nombre = R.string.qr,
                type = ButtonType.SECONDARY
            )
        )
    }
}

