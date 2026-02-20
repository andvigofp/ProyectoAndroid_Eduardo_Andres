@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componentePerfilUsuario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.example.proyecto_eduardo_andres.modelo.PerfilUsuarioDto

/**
 * @author Andrés
 * @see Componenente Campo textos
 * @param   perfilUsarioData: Campos de textos para introducir datos del usuario
 */
@Composable
fun CampoPerfilUsuario(
    perfilUsarioData: PerfilUsuarioDto,
    onPerfilUsuarioData: (PerfilUsuarioDto) -> Unit,
    isEditing: Boolean = false // <- agregado para controlar la edición
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary

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
                color = primaryColor,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = perfilUsarioData.nombreUsuaro,
                onValueChange = { onPerfilUsuarioData(perfilUsarioData.copy(nombreUsuaro = it)) },
                singleLine = true,
                enabled = isEditing, // <- controla edición
                modifier = Modifier.weight(2f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }


        // EMAIL (SIEMPRE BLOQUEADO POR SEGURIDAD)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Email",
                color = primaryColor,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = perfilUsarioData.email,
                onValueChange = {},
                singleLine = true,
                enabled = false,
                readOnly = true,
                modifier = Modifier.weight(2f),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }

        // PASSWORD
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Password",
                color = secondaryColor,
                modifier = Modifier.weight(1f)
            )
            TextField(
                value = perfilUsarioData.password,
                onValueChange = { onPerfilUsuarioData(perfilUsarioData.copy(password = it)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                enabled = isEditing, // <- controla edición
                modifier = Modifier.weight(2f),
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
fun CampoPerfilUsuarioPreview() {
    var perfilUsuarioData by remember { mutableStateOf(PerfilUsuarioDto()) }

    Column {
        Text("Edición deshabilitada")
        CampoPerfilUsuario(
            perfilUsarioData = perfilUsuarioData,
            onPerfilUsuarioData = { perfilUsuarioData = it },
            isEditing = false
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Edición habilitada")
        CampoPerfilUsuario(
            perfilUsarioData = perfilUsuarioData,
            onPerfilUsuarioData = { perfilUsuarioData = it },
            isEditing = true
        )
    }
}