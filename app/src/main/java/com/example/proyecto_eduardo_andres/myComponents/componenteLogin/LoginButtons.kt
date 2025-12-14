@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteLogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
 * @see Componenente Button Loging
 * @param loginButtonTexts: El botón Acceder si los datos son correctos, inicia la sesión, te llevara a la pantalla de VideclubOnlinePeliculas
 * @param loginButtonTexts: El botón Crear Usuario te lleva a la pantalla de Crear Usuario
 * @param loginButtonTexts: El botón Recuperar contraseña te lleva a la pantalla Recuperar contraseña
 *
 */
@Composable
fun LoginButtons(
    accederButton: ButtonData,
    crearUsuarioButton: ButtonData,
    recuperarButton: ButtonData,
    enabledAcceder: Boolean,
    onAccederClick: () -> Unit,
    onCrearUsuarioClick: () -> Unit,
    onRecuperarPasswordClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppButton(
            data = accederButton.copy(enabled = enabledAcceder),
            onClick = onAccederClick,
            modifier = Modifier.fillMaxWidth().height(60.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppButton(
                data = crearUsuarioButton,
                onClick = onCrearUsuarioClick,
                modifier = Modifier.weight(1f).height(60.dp)
            )

            AppButton(
                data = recuperarButton,
                onClick = onRecuperarPasswordClick,
                modifier = Modifier.weight(1f).height(60.dp)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginButtonPreview() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceBright) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(24.dp)
                ) {
                    // Por ejemplo, accesder habilitado solo si email y password tienen datos
                    var enabledAcceder by remember { mutableStateOf(false) }

                    LoginButtons(
                        accederButton = ButtonData(nombre = R.string.acceder, type = ButtonType.PRIMARY),
                        crearUsuarioButton = ButtonData(nombre = R.string.crear_usuario, type = ButtonType.SECONDARY),
                        recuperarButton = ButtonData(nombre = R.string.recuperar_contrasenha, type = ButtonType.DANGER),
                        enabledAcceder = enabledAcceder,   // <-- ahora sí lo pasamos
                        onAccederClick = {},
                        onCrearUsuarioClick = {},
                        onRecuperarPasswordClick = {}
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Para probar en preview, un botón que habilite "Acceder"
                    Button(onClick = { enabledAcceder = !enabledAcceder }) {
                        Text("Toggle Acceder")
                    }
                }
            }
        }
    }
}
