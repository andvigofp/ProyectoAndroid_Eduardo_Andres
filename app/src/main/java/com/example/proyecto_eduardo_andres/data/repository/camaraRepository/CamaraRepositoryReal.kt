package com.example.proyecto_eduardo_andres.data.repository.camaraRepository

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.proyecto_eduardo_andres.R
import java.io.File

class CamaraRepositoryReal(
    private val context: Context
) : ICamaraRepository {

    override fun hacerFoto(
        onSuccessUri: (Uri) -> Unit,
        onSuccessDrawable: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {

            // Verificar si el dispositivo tiene cámara
            val tieneCamara = context.packageManager
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)

            if (!tieneCamara) {
                // No hay cámara → imagen simulada
                onSuccessDrawable(R.drawable.fake_camera_image)
                return
            }

            // Crear archivo temporal
            val archivo = File(
                context.filesDir,
                "foto_${System.currentTimeMillis()}.jpg"
            )

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                archivo
            )

            // 📸 Abrir cámara real
            onSuccessUri(uri)

        } catch (e: Exception) {
            onError(e)
        }
    }

    override fun leerQr(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onSuccess("QR-REAL")
    }
}
