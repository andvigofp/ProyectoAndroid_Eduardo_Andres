package com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun campoCrearUusario(
    crearUsuarioData : crearUsuarioData,
    oncrearUusarioData:  (crearUsuarioData) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        //Nombre
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nombre Usuario",
                color = Color(0xFF0D47A1),
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = crearUsuarioData.nombre,
                onValueChange = { oncrearUusarioData(crearUsuarioData.copy(nombre = it)) },
                singleLine = true,
                modifier = Modifier.weight(2f)
            )

            // PASSWORD
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Password",
                    color = Color(0xFF512DA8),
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = crearUsuarioData.password,
                    onValueChange = { oncrearUusarioData(crearUsuarioData.copy(password = it)) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.weight(2f)
                )
            }

            // RECUPERAR PASSWORD
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Password",
                    color = Color(0xFF512DA8),
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = crearUsuarioData.repeatPassword,
                    onValueChange = { oncrearUusarioData(crearUsuarioData.copy(repeatPassword = it)) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.weight(2f)
                )
                // Email
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Email",
                        color = Color(0xFF0D47A1),
                        modifier = Modifier.weight(1f)
                    )
                    TextField(
                        value = crearUsuarioData.email,
                        onValueChange = { oncrearUusarioData(crearUsuarioData.copy(email = it)) },
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )

                    //Repita Email
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Email",
                            color = Color(0xFF0D47A1),
                            modifier = Modifier.weight(1f)
                        )
                        TextField(
                            value = crearUsuarioData.repeatEmail,
                            onValueChange = { oncrearUusarioData(crearUsuarioData.copy(repeatEmail = it)) },
                            singleLine = true,
                            modifier = Modifier.weight(2f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CamposCrearUsuarioPreview() {
    var crearUsuarioData by remember { mutableStateOf(value = crearUsuarioData()) }

    campoCrearUusario (
        crearUsuarioData = crearUsuarioData,
        oncrearUusarioData = { crearUsuarioData = it }
    )
}