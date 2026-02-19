package com.example.proyecto_eduardo_andres.modelo

import androidx.annotation.StringRes

/**
 * @author Andrés
 * @param titulo ID del recurso string que describe el título del proyecto.
 * @param descripcion ID del recurso string que describe la descripción del proyecto.
 * @param integrantes Lista de objetos ContactoDto que representan a los integrantes del proyecto.
 */
data class InfoProyectoDto(
    @StringRes val titulo: Int,
    @StringRes val descripcion: Int,
    val integrantes: List<ContactoDto>
)

/**
 * @author Andrés
 * @param nombre ID del recurso string que describe el nombre del integrante.
 * @param email ID del recurso string que describe el correo electrónico del integrante.
 */
data class ContactoDto(
    @StringRes val nombre: Int,
    @StringRes val email: Int
)
