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

    override fun leerQr(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onSuccess("QR-1234-FAKE")
    }
}
