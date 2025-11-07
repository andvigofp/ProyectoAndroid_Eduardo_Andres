package com.example.proyecto_eduardo_andres.myComponents.componenteCamara

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @author Andrés
 * @see Componenente Camara
 * @param camaraData: Una simulación de camara
 */
@Composable
fun CamaraComponent(camaraData: CamaraData) {
    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f) // ocupa gran parte de la pantalla
            .padding(16.dp)
            .border(2.dp, colors.outline, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (camaraData.imagenCamara != null) {
            Image(
                painter = painterResource(id = camaraData.imagenCamara),
                contentDescription = camaraData.descripcion,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            // Simulación de cámara (si no hay imagen real)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.tertiary), // Color agradable del tema
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Cámara simulada",
                    tint = colors.onTertiaryContainer, // Contraste adecuado
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CamaraComponentPreview() {
    val camaraDemo = CamaraData(
        descripcion = "Vista previa de la cámara"
    )

    MaterialTheme {
        CamaraComponent(camaraData = camaraDemo)
    }
}
