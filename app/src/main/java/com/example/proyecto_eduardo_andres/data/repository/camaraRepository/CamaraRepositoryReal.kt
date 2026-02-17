package com.example.proyecto_eduardo_andres.data.repository.camaraRepository

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.proyecto_eduardo_andres.R
import java.io.File

/**
 *
 * Implementación de ICamaraRepository que utiliza:
 * - FileProvider para generar una URI segura
 * - Cámara real del dispositivo si está disponible
 *
 * Si el dispositivo no dispone de cámara,
 * se devuelve una imagen simulada como fallback.
 *
 * @author Eduardo
 * @see Implementación real del repositorio de cámara
 *
 * @param context Context necesario para:
 * - Verificar si existe hardware de cámara
 * - Acceder a filesDir
 * - Obtener recursos del sistema
 * - Generar URI mediante FileProvider
 */
class CamaraRepositoryReal(
    private val context: Context
) : ICamaraRepository {

    /**
     * Lanza el proceso de captura de fotografía.
     *
     * @param onSuccessUri Callback que devuelve la URI
     *        generada cuando el dispositivo tiene cámara real.
     *
     * @param onSuccessDrawable Callback que devuelve un drawable
     *        simulado cuando el dispositivo no dispone de cámara.
     *
     * @param onError Callback que devuelve la excepción
     *        en caso de error durante la creación del archivo
     *        o generación de la URI.
     */
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

            // Abrir cámara real
            onSuccessUri(uri)

        } catch (e: Exception) {
            onError(e)
        }
    }

    /**
     * Simula la lectura de un código QR.
     *
     * @param onSuccess Callback que devuelve el texto leído
     *        del código QR.
     *
     * @param onError Callback que devuelve la excepción
     *        en caso de error durante la lectura.
     */
    override fun leerQr(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onSuccess("QR-REAL")
    }
}