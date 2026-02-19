package com.example.proyecto_eduardo_andres.data.repository.camaraRepository

import android.net.Uri
import com.example.proyecto_eduardo_andres.R

class CamaraRepositoryInMemory : ICamaraRepository {

    override fun hacerFoto(
        onSuccessUri: (Uri) -> Unit,
        onSuccessDrawable: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            onSuccessDrawable(R.drawable.fake_camera_image)
        } catch (e: Throwable) {
            onError(e)
        }
    }

    /**
     * Ejecuta el proceso de lectura de código QR.
     *
     * @param onSuccess Callback que devuelve el texto
     *        decodificado del código QR.
     *
     * @param onError Callback que devuelve la excepción
     *        en caso de error durante la lectura.
     */
    override fun leerQr(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onSuccess("QR-1234-FAKE")
    }
}