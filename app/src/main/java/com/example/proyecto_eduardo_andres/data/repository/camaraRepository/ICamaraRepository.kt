package com.example.proyecto_eduardo_andres.data.repository.camaraRepository

import android.net.Uri

interface ICamaraRepository {

    fun hacerFoto(
        onSuccessUri: (Uri) -> Unit,
        onSuccessDrawable: (Int) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun leerQr(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    )
}

