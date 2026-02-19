package com.example.proyecto_eduardo_andres.modelo

import com.example.proyecto_eduardo_andres.data.repository.mediaItemRepository.MediaItemData

/**
 * Modelo de datos utilizado en la capa de presentación (UI)
 * para representar una película dentro del VideoClub Online.
 *
 * Esta clase implementa la interfaz MediaItemData,
 * permitiendo reutilizar propiedades comunes como:
 * - nombre
 * - categoria
 * - imagen
 *
 * @author Andrés
 *
 * @param id Identificador único de la película.
 *
 * @param nombre Identificador de recurso string (R.string.*)
 * que representa el nombre de la película.
 *
 * @param categoria Identificador de recurso string (R.string.*)
 * que representa la categoría a la que pertenece la película.
 *
 * @param imagen Identificador de recurso drawable (R.drawable.*)
 * que representa la imagen asociada a la película.
 *
 * @param descripcion Identificador de recurso string (R.string.*)
 * que representa la descripción de la película.
 */
data class VideoClubOnlinePeliculasData(
    val id: String,
    override val nombre: Int,
    override val categoria: Int,
    override val imagen: Int?,
    val descripcion: Int
) :MediaItemData

/**
 * Función de ayuda (factory function) para crear
 * instancias de VideoClubOnlinePeliculasData
 * de forma más legible.
 *
 * @param id Identificador único de la película.
 * @param categoria Recurso string que representa la categoría.
 * @param imagen Recurso drawable asociado a la película.
 * @param nombre Recurso string del nombre de la película.
 * @param descripcion Recurso string de la descripción.
 *
 * @return Objeto VideoClubOnlinePeliculasData correctamente inicializado.
 */
fun pelicula(id: String, categoria: Int, imagen: Int?, nombre: Int, descripcion: Int) =
    VideoClubOnlinePeliculasData(
        id = id,
        nombre = nombre,
        categoria = categoria,
        imagen = imagen,
        descripcion = descripcion
    )