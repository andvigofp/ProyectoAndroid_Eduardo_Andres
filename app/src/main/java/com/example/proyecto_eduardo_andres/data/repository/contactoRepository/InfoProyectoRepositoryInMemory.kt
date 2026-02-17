package com.example.proyecto_eduardo_andres.data.repository.contactoRepository

import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.modelo.ContactoDto
import com.example.proyecto_eduardo_andres.modelo.InfoProyectoDto

/**
 *
 * Devuelve datos estáticos definidos en recursos (string.xml)
 * para mostrar la información del proyecto, integrantes y
 * correos electrónicos.
 *
 *
 * @author Eduardo
 *
 * @description
 * Implementación en memoria de [IInfoProyectoRepository].
 */
class InfoProyectoRepositoryInMemory : IInfoProyectoRepository {

    /**
     * Obtiene la información general del proyecto.
     *
     * @param onSuccess Callback que devuelve un objeto [InfoProyectoDto]
     * con el título, descripción e integrantes del proyecto.
     *
     * @param onError Callback que devuelve un [Throwable]
     * en caso de producirse un error durante la creación
     * o devolución de los datos.
     */
    override fun obtenerInfo(
        onSuccess: (InfoProyectoDto) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            val data = InfoProyectoDto(
                titulo = R.string.titulo_info_proyecto,
                descripcion = R.string.descripcion_info_proyecto,
                integrantes = listOf(
                    ContactoDto(
                        nombre = R.string.nombre_contactoA,
                        email = R.string.email_contactoA
                    ),
                    ContactoDto(
                        nombre = R.string.nombre_contactoB,
                        email = R.string.email_contactoB
                    )
                )
            )

            onSuccess(data)
        } catch (e: Exception) {
            onError(e)
        }
    }
}