package com.example.proyecto_eduardo_andres.myComponents.componenteLogin

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
fun CamposLogin(
    loginData: loginData,
    onLoginDataChange: (loginData) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // EMAIL
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
                value = loginData.email,
                onValueChange = { onLoginDataChange(loginData.copy(email = it)) },
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }

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
                value = loginData.password,
                onValueChange = { onLoginDataChange(loginData.copy(password = it)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.weight(2f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CamposLoginPreview() {
    var loginData by remember { mutableStateOf(loginData()) }

    CamposLogin(
        loginData = loginData,
        onLoginDataChange = { loginData = it }
    )
}
