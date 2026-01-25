package com.example.proyecto_eduardo_andres.repository.qrRepository

interface IQRRepository {
    fun obtenerQRData(
        userId: Int,
        onError: (Throwable) -> Unit,
        onSuccess: (String) -> Unit
    )
}

