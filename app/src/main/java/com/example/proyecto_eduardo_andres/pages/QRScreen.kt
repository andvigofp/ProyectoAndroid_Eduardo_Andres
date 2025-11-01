package com.example.proyecto_eduardo_andres.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.myComponents.componenteQR.QRData
import com.example.proyecto_eduardo_andres.myComponents.componenteQR.QRView
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar

@Composable
fun QRScreen(
    qrData: QRData,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Scaffold(
        topBar = {
            toolBar(
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onCameraClick = onCameraClick,
                onProfileClick = onProfileClick,
                onLogoutClick = onLogoutClick
            )
        },
        bottomBar = {
            // Bottom bar con color bonito del tema
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        content = { paddingValues ->
            // Contenido del QR centrado debajo del Toolbar
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                QRView(qrData = qrData)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun QRScreenPreview() {
    QRScreen(
        qrData = QRData("https://ejemplo.com"),
        onBackClick = {},
        onHomeClick = {},
        onCameraClick = {},
        onProfileClick = {},
        onLogoutClick = {}
    )
}
