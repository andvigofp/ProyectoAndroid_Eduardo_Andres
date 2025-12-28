package com.example.proyecto_eduardo_andres.repository.CamaraRepository

import com.example.proyecto_eduardo_andres.R

class CamaraRepositoryInMemory : ICamaraRepository {

    override fun hacerFoto(
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            // Simulamos una foto devolviendo un drawable
            onSuccess(R.drawable.fake_camera_image)
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun leerQr(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            // Simulación de lectura QR
            onSuccess("QR-1234-FAKE")
        } catch (e: Throwable) {
            onError(e)
        }
    }
}
