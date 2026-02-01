package com.example.proyecto_eduardo_andres.data.repository.qrRepository

interface IQRRepository {
    fun obtenerQRData(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (String) -> Unit
    )
}



