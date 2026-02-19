package com.example.proyecto_eduardo_andres.data.repository.qrRepository

/**
 *
 * Implementación en memoria del repositorio QR.
 *
 *
 * Simula la generación de un código QR para un usuario.
 * Se utiliza principalmente en modo offline o en Previews.
 *
 * @author Eduardo
 * @see IQRRepository
 */
class QRRepositoryInMemory :
    IQRRepository {
    override fun obtenerQRData(
        userId: String,
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



