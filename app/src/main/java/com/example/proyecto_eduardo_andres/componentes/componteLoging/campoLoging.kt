package com.example.proyecto_eduardo_andres.componentes.componteLoging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
fun CamposLogin(
    loginData: LoginData,
    onLoginDataChange: (LoginData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // EMAIL
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Email",
                color = Color(0xFF0D47A1), // azul oscuro (coherente con toolbar)
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                value = loginData.email,
                onValueChange = { onLoginDataChange(loginData.copy(email = it)) },
                singleLine = true,
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 8.dp)
            )
        }

        // PASSWORD
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Password",
                color = Color(0xFF512DA8), // violeta suave
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                value = loginData.password,
                onValueChange = { onLoginDataChange(loginData.copy(password = it)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CamposLoginPreview() {
    var loginData by remember { mutableStateOf(LoginData()) }

    CamposLogin(
        loginData = loginData,
        onLoginDataChange = { loginData = it }
    )
}
