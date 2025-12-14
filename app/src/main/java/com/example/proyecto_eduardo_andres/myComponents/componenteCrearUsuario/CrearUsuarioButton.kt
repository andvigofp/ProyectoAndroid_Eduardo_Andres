@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun CrearUsuarioButtons(
    crearButton: ButtonData,
    cancelarButton: ButtonData,
    enabledCrear: Boolean,
    onCrearClick: () -> Unit,
    onCancelarClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Botón Crear Usuario
        AppButton(
            data = crearButton.copy(enabled = enabledCrear),
            onClick = onCrearClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Cancelar
        AppButton(
            data = cancelarButton,
            onClick = onCancelarClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrearUsuarioButtonsPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(24.dp)
                ) {
                    var enabledCrear by remember { mutableStateOf(false) }

                    CrearUsuarioButtons(
                        crearButton = ButtonData(
                            nombre = R.string.crear_usuario,
                            type = ButtonType.PRIMARY
                        ),
                        cancelarButton = ButtonData(
                            nombre = R.string.cancelar,
                            type = ButtonType.SECONDARY
                        ),
                        enabledCrear = enabledCrear,
                        onCrearClick = {},
                        onCancelarClick = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //Para probar en el preView
                    Button(onClick = { enabledCrear = !enabledCrear }) {
                        Text("Toggle Crear")
                    }
                }
            }
        }
    }
}
