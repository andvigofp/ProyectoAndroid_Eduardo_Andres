@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.componente.componenteCamara

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.proyecto_eduardo_andres.modelo.CamaraDto
import com.example.proyecto_eduardo_andres.R

/**
 * @author Andrés
 * @see Componenente Camara
 * @param camaraData: Una simulación de camara
 */
@Composable
fun CamaraComponent(camaraData: CamaraDto) {

    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .padding(16.dp)
            .border(2.dp, colors.outline, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {

        when {
            //Imagen real tomada con cámara
            camaraData.imagenUri != null -> {
                Image(
                    painter = rememberAsyncImagePainter(camaraData.imagenUri),
                    contentDescription = stringResource(camaraData.descripcion),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Imagen simulada
            camaraData.imagenDrawable != null -> {
                Image(
                    painter = painterResource(id = camaraData.imagenDrawable),
                    contentDescription = stringResource(camaraData.descripcion),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Cámara vacía
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.tertiary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = stringResource(R.string.camara_simulada),
                        tint = colors.onTertiaryContainer,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CamaraComponentPreview() {
    val camaraDemo = CamaraDto(
        descripcion = R.string.vista_previa_camara
    )

    MaterialTheme {
        CamaraComponent(camaraData = camaraDemo)
    }
}