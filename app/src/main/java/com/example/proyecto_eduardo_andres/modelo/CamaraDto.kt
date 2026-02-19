package com.example.proyecto_eduardo_andres.modelo

import android.net.Uri

/**
 * @author Andrés
 * @param imagenUri Uri(Es la dirección donde está guardada la imagen) de la imagen capturada por la cámara.
 * @param imagenDrawable ID del recurso drawable asociado a la imagen.
 * @param descripcion ID del recurso string que describe el tipo de imagen.
 */
data class CamaraDto(
    val imagenUri: Uri? = null,
    val imagenDrawable: Int? = null,
    val descripcion: Int
)
