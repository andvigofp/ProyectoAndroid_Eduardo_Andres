package com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BotonAlquilarSeries(
    botonAlquilar: BotonAlquilarDevolverData,
    botonDevolver: BotonAlquilarDevolverData,
    onAlquilarClick: () -> Unit = {},
    onDevolverClick: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
    ) {
        // Botón Alquilar
        Button(
            onClick = onAlquilarClick,
            colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = botonAlquilar.nombreBoton,
                style = typography.labelLarge.copy(color = colors.onPrimary)
            )
        }

        // Botón Devolver
        Button(
            onClick = onDevolverClick,
            colors = ButtonDefaults.buttonColors(containerColor = colors.tertiary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = botonDevolver.nombreBoton,
                style = typography.labelLarge.copy(color = colors.onPrimary)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BotonAlquilarSeriesPreview() {
    MaterialTheme {
        BotonAlquilarSeries(
            botonAlquilar = BotonAlquilarDevolverData("Alquilar"),
            botonDevolver = BotonAlquilarDevolverData("Devolver")
        )
    }
}
