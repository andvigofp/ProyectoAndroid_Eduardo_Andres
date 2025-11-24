@file:Suppress("ALL")
package com.example.proyecto_eduardo_andres.myComponents.componenteQR

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.proyecto_eduardo_andres.viewData.QRData.QRData
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.example.proyecto_eduardo_andres.R

/**
 * @author Eduardo
 * @see Componenente Campo QR
 * @param writer: Con el movíl escanea el código QR
 */
// Función para generar Bitmap de QR
fun generateQRCode(text: String, size: Int): Bitmap {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size)
    val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    for (x in 0 until size) {
        for (y in 0 until size) {
            bmp.setPixel(
                x,
                y,
                if (bitMatrix.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE
            )
        }
    }
    return bmp
}

@Composable
fun QRView(
    qrData: QRData,
    size: Dp = 250.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray), // fondo de la vista
        contentAlignment = Alignment.Center
    ) {
        val bitmap = remember(qrData) {
            generateQRCode(qrData.data, size.value.toInt())
        }
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = stringResource(R.string.codigo_qr),
            modifier = Modifier
                .size(size)
                .background(Color.White, RoundedCornerShape(8.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QRViewPreview() {
    QRView(qrData = QRData(stringResource(R.string.qrData)))
}