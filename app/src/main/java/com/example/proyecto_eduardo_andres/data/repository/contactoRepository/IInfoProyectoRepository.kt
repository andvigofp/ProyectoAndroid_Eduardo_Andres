package com.example.proyecto_eduardo_andres.data.repository.contactoRepository

import com.example.proyecto_eduardo_andres.modelo.InfoProyectoDto

/**
 *
 * Esta interfaz permite desacoplar la fuente de datos,
 * pudiendo usar implementación en memoria, Retrofit o Room.
 *
 *  @author Eduardo
 *  @description
 *  Define las operaciones necesarias para obtener la información
 *  general del proyecto (título, descripción e integrantes).
 */
interface IInfoProyectoRepository {
    fun obtenerInfo(
        onSuccess: (InfoProyectoDto) -> Unit,
        onError: (Throwable) -> Unit
    )
}
