package com.example.proyecto_eduardo_andres.vista.componente.componenteSearchSeriesPeliculas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.coloAuzlClaro
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.repository.mediaItemRepository.MediaItemData

@Composable
fun MediaItem(
    item: com.example.proyecto_eduardo_andres.data.repository.mediaItemRepository.MediaItemData,
    onClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(coloAuzlClaro, RoundedCornerShape(12.dp))
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(8.dp)
    ) {
        // Solo llamamos a painterResource si imagen NO es null
        item.imagen?.let { imagenId ->
            Image(
                painter = painterResource(id = imagenId),
                contentDescription = stringResource(item.nombre),
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } ?: run {
            // Si imagen es null mostramos un placeholder
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(R.string.img),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = stringResource(item.nombre),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = stringResource(item.categoria),
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }
    }
}
