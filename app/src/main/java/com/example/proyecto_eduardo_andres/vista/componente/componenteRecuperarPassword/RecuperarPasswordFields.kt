@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteRecuperarPassword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.viewmodel.ustate.RecuperarPasswordUiState
import com.example.proyecto_eduardo_andres.R

/**
 * @author Andres
 * @see Componenente Campo Texto
 * @param recuperarPasswordData: Campos de texto que tiene que introducir el usuario
 */
@Composable
fun RecuperarPasswordFields(
    recuperarPasswordData: RecuperarPasswordUiState,
    onRecuperarPasswordData: (RecuperarPasswordUiState) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // EMAIL
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Email",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = recuperarPasswordData.email,
                onValueChange = { onRecuperarPasswordData(recuperarPasswordData.copy(email = it)) },
                singleLine = true,
                modifier = Modifier.weight(2f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
        }

        // PASSWORD
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.password),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = recuperarPasswordData.password,
                onValueChange = { onRecuperarPasswordData(recuperarPasswordData.copy(password = it)) },
                singleLine = true,
                visualTransformation = if (recuperarPasswordData.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.weight(2f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    val image = if (recuperarPasswordData.passwordVisible)
                        Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        onRecuperarPasswordData(recuperarPasswordData.copy(passwordVisible = !recuperarPasswordData.passwordVisible))
                    }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                }
            )
        }

        // REPETIR PASSWORD
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.repetir_password),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = recuperarPasswordData.repeatPassword,
                onValueChange = { onRecuperarPasswordData(recuperarPasswordData.copy(repeatPassword = it)) },
                singleLine = true,
                visualTransformation = if (recuperarPasswordData.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.weight(2f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecuperarPasswordFieldsPreview() {
    var recuperarPasswordData by remember { mutableStateOf(RecuperarPasswordUiState()) }

    RecuperarPasswordFields(
        recuperarPasswordData = recuperarPasswordData,
        onRecuperarPasswordData = { recuperarPasswordData = it }
    )
}