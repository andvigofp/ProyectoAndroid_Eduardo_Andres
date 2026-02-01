@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteLogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import com.example.proyecto_eduardo_andres.viewmodel.ustate.LoginUiState

/**
 * @author Andrés
 * @see Componenente Campo Loging
 * @param  loginData: Campos para inciar sesión
 */
@Composable
fun CamposLogin(
    loginData: LoginUiState,
    onLoginDataChange: (LoginUiState) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
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
                text = stringResource(R.string.email),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            TextField(
                value = loginData.email,
                onValueChange = { onLoginDataChange(loginData.copy(email = it)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.weight(2f).alignByBaseline(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
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
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            TextField(
                value = loginData.password,
                onValueChange = { onLoginDataChange(loginData.copy(password = it)) },
                singleLine = true,
                visualTransformation = if (loginData.passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val text = if (loginData.passwordVisible) "Hide" else "Show"
                    TextButton(onClick = onTogglePasswordVisibility) {
                        Text(text)
                    }
                },
                modifier = Modifier.weight(2f).alignByBaseline(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CamposLoginPreview() {
    var loginData by remember { mutableStateOf(LoginUiState()) }

    CamposLogin(
        loginData = loginData,
        onLoginDataChange = { loginData = it },       // Actualiza todo el state
        onTogglePasswordVisibility = {
            loginData = loginData.copy(passwordVisible = !loginData.passwordVisible)
        } // Toggle para mostrar/ocultar contraseña
    )
}

