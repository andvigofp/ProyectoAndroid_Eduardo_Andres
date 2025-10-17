package com.example.proyecto_eduardo_andres.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyecto_eduardo_andres.componentes.componenteLogin.LoginButtonTexts
import com.example.proyecto_eduardo_andres.componentes.componenteLogin.LoginButtons
import com.example.proyecto_eduardo_andres.componentes.componteLoging.CamposLogin
import com.example.proyecto_eduardo_andres.componentes.componteLoging.LoginData


@Composable
fun LoginScreen(
    userImageUrl: String?, // null si no hay imagen
    onAccederClick: () -> Unit,
    onCrearUsuarioClick: () -> Unit,
    onRecuperarPasswordClick: () -> Unit
) {
    var loginData by remember { mutableStateOf(LoginData()) }

    // Fondo con gradiente llamativo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF512DA8), // Violeta oscuro
                        Color(0xFF7B1FA2), // Violeta medio
                        Color(0xFFE040FB)  // Rosa fuerte
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Contenedor principal (columna centrada con sombra)
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .shadow(12.dp, RoundedCornerShape(24.dp))
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // IMAGEN DE PERFIL EN LA PARTE SUPERIOR
            if (userImageUrl != null) {
                AsyncImage(
                    model = userImageUrl,
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray, CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Usuario sin imagen",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape),
                    tint = Color(0xFF9E9E9E)
                )
            }

            // CAMPOS DE LOGIN
            CamposLogin(
                loginData = loginData,
                onLoginDataChange = { loginData = it }
            )

            // BOTONES DE LOGIN
            LoginButtons(
                onAccederClick = onAccederClick,
                onCrearUsuarioClick = onCrearUsuarioClick,
                onRecuperarPasswordClick = onRecuperarPasswordClick
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            userImageUrl = null, // Cambia por una URL para probar con imagen real
            onAccederClick = {},
            onCrearUsuarioClick = {},
            onRecuperarPasswordClick = {}
        )
    }
}