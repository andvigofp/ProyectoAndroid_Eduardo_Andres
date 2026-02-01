package com.example.proyecto_eduardo_andres.data.repository.camaraRepository

interface ICamaraRepository {

    fun hacerFoto(
        onSuccess: (Int) -> Unit, // devuelve un drawable simulado
        onError: (Throwable) -> Unit
    )

    fun leerQr(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    )
}
