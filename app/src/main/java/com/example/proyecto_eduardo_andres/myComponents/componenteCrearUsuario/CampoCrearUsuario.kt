@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R

/**
 * @author Andrés
 * @see Componenente Campo usuario
 * @param crearUsuarioData: Datos para crear un nuevo usuario
 */
@Composable
fun CampoCrearUsuario(
    crearUsuarioData: CrearUsuarioUiState,
    onCrearUsuarioData: (CrearUsuarioUiState) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onToggleRepeatPasswordVisibility: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // NOMBRE USUARIO
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.nombre_usuario),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            TextField(
                value = crearUsuarioData.nombre,
                onValueChange = {
                    onCrearUsuarioData(crearUsuarioData.copy(nombre = it))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.weight(2f).alignByBaseline()
            )
        }

        // PASSWORD
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.password),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            TextField(
                value = crearUsuarioData.password,
                onValueChange = {
                    onCrearUsuarioData(crearUsuarioData.copy(password = it))
                },
                singleLine = true,
                visualTransformation = if (crearUsuarioData.passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = onTogglePasswordVisibility) {
                        Icon(
                            imageVector = if (crearUsuarioData.passwordVisible)
                                Icons.Default.VisibilityOff
                            else
                                Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.weight(2f).alignByBaseline()
            )
        }

            // REPETIR PASSWORD
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.repetir_password),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            TextField(
                value = crearUsuarioData.repeatPassword,
                onValueChange = {
                    onCrearUsuarioData(crearUsuarioData.copy(repeatPassword = it))
                },
                singleLine = true,
                visualTransformation = if (crearUsuarioData.repeatPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = onToggleRepeatPasswordVisibility) {
                        Icon(
                            imageVector = if (crearUsuarioData.repeatPasswordVisible)
                                Icons.Default.VisibilityOff
                            else
                                Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.weight(2f).alignByBaseline()
            )
        }

        // EMAIL
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.email),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            TextField(
                value = crearUsuarioData.email,
                onValueChange = {
                    onCrearUsuarioData(crearUsuarioData.copy(email = it))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.weight(2f).alignByBaseline()
            )
        }

        // REPETIR EMAIL
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.repetir_email),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            TextField(
                value = crearUsuarioData.repeatEmail,
                onValueChange = {
                    onCrearUsuarioData(crearUsuarioData.copy(repeatEmail = it))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.weight(2f).alignByBaseline()
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CampoCrearUsuarioPreview() {
    var crearUsuarioData by remember { mutableStateOf(CrearUsuarioUiState()) }

    CampoCrearUsuario(
        crearUsuarioData = crearUsuarioData,
        onCrearUsuarioData = { crearUsuarioData = it },
        onTogglePasswordVisibility = {
            crearUsuarioData = crearUsuarioData.copy(
                passwordVisible = !crearUsuarioData.passwordVisible
            )
        },
        onToggleRepeatPasswordVisibility = {
            crearUsuarioData = crearUsuarioData.copy(
                repeatPasswordVisible = !crearUsuarioData.repeatPasswordVisible
            )
        }
    )
}