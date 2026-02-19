package com.example.proyecto_eduardo_andres.data.repository.mediaItemRepository

/**
 * Interfaz base que representa un elemento multimedia genérico
 * dentro del VideoClub (películas o series).
 *
 * Define las propiedades comunes que comparten ambos tipos
 * de contenido para permitir reutilización en componentes UI
 * y repositorios genéricos.
 *
 * Se utiliza principalmente para:
 * - Listados genéricos de contenido.
 * - Componentes reutilizables.
 * - Abstracción entre películas y series.
 *
 * @property nombre Recurso String (R.string) que representa el título.
 * @property categoria Recurso String (R.string) que representa la categoría.
 * @property imagen Recurso Drawable (R.drawable) opcional que representa la imagen.
 *
 * @author Eduardo
 * @see VideoClubOnlinePeliculasData
 * @see VideoClubOnlineSeriesData
 */
interface MediaItemData {
    val nombre: Int
    val categoria: Int
    val imagen: Int?
}
