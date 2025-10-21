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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyecto_eduardo_andres.myComponents.componenteLogin.LoginButtons
import com.example.proyecto_eduardo_andres.myComponents.componenteLogin.CamposLogin
import com.example.proyecto_eduardo_andres.myComponents.componenteLogin.loginButtonTexts
import com.example.proyecto_eduardo_andres.myComponents.componenteLogin.loginData


@Composable
fun LoginScreen(
    userImageUrl: String?, // null si no hay imagen
    onAccederClick: () -> Unit,
    onCrearUsuarioClick: () -> Unit,
    onRecuperarPasswordClick: () -> Unit
) {
    var loginData by remember { mutableStateOf(loginData()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Degradado superior (toolbar)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF3F51B5),
                            Color(0xFF512DA8)
                        )
                    )
                )
        )

        // Degradado inferior (background detrás de los botones)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.BottomCenter) // Pegado al fondo
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF512DA8),
                            Color(0xFF3F51B5)
                        )
                    )
                )
        )

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 95.dp, bottom = 32.dp), // padding inferior para no chocar con bottom
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Título LOGIN
            Text(
                text = "LOGIN",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Tarjeta del formulario
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(Color.White, RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                // Imagen de perfil
                if (userImageUrl != null) {
                    AsyncImage(
                        model = userImageUrl,
                        contentDescription = "Imagen de perfil",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Usuario sin imagen",
                        modifier = Modifier.size(120.dp),
                        tint = Color(0xFF9E9E9E)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campos de login
                CamposLogin(
                    loginData = loginData,
                    onLoginDataChange = { loginData = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botones
                LoginButtons(
                    loginButtonTexts= loginButtonTexts(),
                    onAccederClick = onAccederClick,
                    onCrearUsuarioClick = onCrearUsuarioClick,
                    onRecuperarPasswordClick = onRecuperarPasswordClick
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            userImageUrl = null,
            onAccederClick = {},
            onCrearUsuarioClick = {},
            onRecuperarPasswordClick = {}
        )
    }
}
