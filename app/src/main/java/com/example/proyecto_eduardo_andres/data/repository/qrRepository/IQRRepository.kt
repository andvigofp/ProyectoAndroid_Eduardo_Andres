package com.example.proyecto_eduardo_andres.data.repository.qrRepository

/**
 *
 * Interfaz que define la obtención de la información necesaria
 * para generar o mostrar un código QR asociado a un usuario.
 *
 * @author Eduardo
 * @see Repositorio encargado de generar los datos del código QR
 */
interface IQRRepository {
    fun obtenerQRData(
        userId: String,
        onError: (Throwable) -> Unit,
        onSuccess: (String) -> Unit
    )
}



