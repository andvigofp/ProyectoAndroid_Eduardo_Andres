package com.example.proyecto_eduardo_andres.modelo

import com.example.proyecto_eduardo_andres.data.repository.mediaItemRepository.MediaItemData

/**
 * Modelo de datos utilizado en la capa de presentación (UI)
 * para representar una serie dentro del VideoClub Online.
 *
 * Esta clase implementa la interfaz MediaItemData,
 * permitiendo compartir propiedades comunes con películas,
 * como:
 * - nombre
 * - categoria
 * - imagen
 *
 * @author Eduaardo
 * @param id Identificador único de la serie.
 *
 * @param nombre Identificador de recurso string (R.string.*)
 * que representa el nombre de la serie.
 *
 * @param categoria Identificador de recurso string (R.string.*)
 * que representa la categoría a la que pertenece la serie.
 *
 * @param imagen Identificador de recurso drawable (R.drawable.*)
 * que representa la imagen asociada a la serie.
 *
 * @param descripcion Identificador de recurso string (R.string.*)
 * que representa la descripción de la serie.
 */
data class VideoClubOnlineSeriesData(
    val id: String,
    override val nombre: Int,
    override val categoria: Int,
    override val imagen: Int?,
    val descripcion: Int
) : MediaItemData

/**
* Función de ayuda (factory function) para crear
* instancias de VideoClubOnlineSeriesData
* de forma más legible y simplificada.
*
* @param id Identificador único de la serie.
* @param categoria Recurso string que representa la categoría.
* @param imagen Recurso drawable asociado a la serie.
* @param nombre Recurso string del nombre de la serie.
* @param descripcion Recurso string de la descripción.
*
* @return Objeto VideoClubOnlineSeriesData correctamente inicializado.
*/
fun serie(id: String, categoria: Int, imagen: Int?, nombre: Int, descripcion: Int) =
    VideoClubOnlineSeriesData(
        id = id,
        nombre = nombre,
        categoria = categoria,
        imagen = imagen,
        descripcion = descripcion
    )