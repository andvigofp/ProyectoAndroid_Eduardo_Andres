package com.example.proyecto_eduardo_andres.myComponents.componenteRecuperarPassword

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecuperarPasswordFields(
   recuperarPasswordData: RecuperarPasswordData,
   onRecuperarPasswordData: (RecuperarPasswordData) -> Unit
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
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = recuperarPasswordData.email,
                onValueChange = { onRecuperarPasswordData(recuperarPasswordData.copy(email = it)) },
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
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = recuperarPasswordData.password,
                onValueChange = { onRecuperarPasswordData(recuperarPasswordData.copy(password = it)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.weight(2f)
            )
        }

        // REPETIR PASSWORD
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Repetir Password",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = recuperarPasswordData.repitaPassword,
                onValueChange = { onRecuperarPasswordData(recuperarPasswordData.copy(repitaPassword = it)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.weight(2f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecuperarPasswordFieldsPreview() {
    var recuperarPasswordData by remember { mutableStateOf(RecuperarPasswordData()) }

    RecuperarPasswordFields(
        recuperarPasswordData = recuperarPasswordData,
        onRecuperarPasswordData = {recuperarPasswordData = it}
    )
}
