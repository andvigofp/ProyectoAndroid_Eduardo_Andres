@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteLogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.viewData.LogingData.LoginData

/**
 * @author Andrés
 * @see Componenente Campo Loging
 * @param  loginData: Campos para inciar sesión
 */
@Composable
fun CamposLogin(
    loginData: LoginData,
    onLoginDataChange: (LoginData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // EMAIL
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.email),
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .weight(1f)
                    .alignByBaseline()
            )
            TextField(
                value = loginData.email,
                onValueChange = { onLoginDataChange(loginData.copy(email = it)) },
                singleLine = true,
                modifier = Modifier
                    .weight(2f)
                    .alignByBaseline()
            )
        }

        // PASSWORD
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.password),
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .weight(1f)
                    .alignByBaseline()
            )
            TextField(
                value = loginData.password,
                onValueChange = { onLoginDataChange(loginData.copy(password = it)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .weight(2f)
                    .alignByBaseline()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CamposLoginsPreview() {
    var loginData by remember { mutableStateOf(LoginData()) }

    CamposLogin(
        loginData = loginData,
        onLoginDataChange = { loginData = it }
    )
}