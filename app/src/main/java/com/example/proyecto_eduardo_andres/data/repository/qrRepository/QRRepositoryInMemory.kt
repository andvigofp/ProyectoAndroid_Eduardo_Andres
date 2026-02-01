package com.example.proyecto_eduardo_andres.data.repository.qrRepository

class QRRepositoryInMemory :
    com.example.proyecto_eduardo_andres.data.repository.qrRepository.IQRRepository {
    override fun obtenerQRData(
        userId: Int,
        onError: (Throwable) -> Unit,
        onSuccess: (String) -> Unit
    ) {
        try {
            // Simulación de datos QR
            val qrData = "QR-USER-$userId-${System.currentTimeMillis()}"
            onSuccess(qrData)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}



