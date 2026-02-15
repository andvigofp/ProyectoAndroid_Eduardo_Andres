package com.example.proyecto_eduardo_andres.data.repository.contactoRepository

import com.example.proyecto_eduardo_andres.modelo.InfoProyectoDto

interface IInfoProyectoRepository {
    fun obtenerInfo(
        onSuccess: (InfoProyectoDto) -> Unit,
        onError: (Throwable) -> Unit
    )
}
