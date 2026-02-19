package com.example.proyecto_eduardo_andres.data.repository.camaraRepository

import android.net.Uri

/**
 *
 * Interfaz que define las operaciones relacionadas con:
 * - Captura de fotografías
 * - Lectura de códigos QR
 *
 * Puede tener implementación real (CameraX) o simulada (InMemory).
 *
 * @author Andrés
 * @see Repositorio encargado de gestionar la cámara y lectura QR
 */
interface ICamaraRepository {

    /**
     * Lanza el proceso de captura de imagen.
     *
     * @param onSuccessUri Callback que devuelve la URI generada
     *        cuando se utiliza cámara real.
     *
     * @param onSuccessDrawable Callback que devuelve un drawable
     *        simulado cuando el dispositivo no dispone de cámara
     *        o se usa modo preview.
     *
     * @param onError Callback que devuelve la excepción producida
     *        en caso de error durante el proceso.
     */
    fun hacerFoto(
        onSuccessUri: (Uri) -> Unit,
        onSuccessDrawable: (Int) -> Unit,
        onError: (Throwable) -> Unit
    )

    /**
     * Ejecuta el proceso de lectura de código QR.
     *
     * @param onSuccess Callback que devuelve el texto
     *        decodificado del código QR.
     *
     * @param onError Callback que devuelve la excepción
     *        en caso de error durante la lectura.
     */
    fun leerQr(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    )
}

