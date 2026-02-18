package com.example.proyecto_eduardo_andres.modelo

/**
 * Enumeración que define los distintos tipos de botón
 * utilizados en la interfaz de usuario.
 *
 *
 * Tipos disponibles:
 * - PRIMARY   → Botón principal de acción.
 * - SECONDARY → Acción alternativa.
 * - DANGER    → Acción destructiva o crítica (ej: eliminar).
 * - OUTLINE   → Botón con estilo contorneado.
 *
 * @Author Andrés
 */
enum class ButtonType { PRIMARY, SECONDARY, DANGER, OUTLINE }

/**
* Modelo de datos que representa la configuración
* de un botón en la interfaz de usuario.
*
* Se utiliza para construir componentes reutilizables
* en Jetpack Compose, permitiendo definir:
* - Texto del botón
* - Tipo visual
* - Estado habilitado o deshabilitado
*
* @Author Andrés
* @param nombre Identificador del recurso string (R.string.*)
* Determina el estilo visual aplicado (colores, borde, etc.).
* Por defecto se establece como PRIMARY.
*
* @param type Tipo de botón definido por la enumeración ButtonType.
* Permite controlar la interacción del usuario según el estado de la pantalla,
*/
data class ButtonData(
    val nombre: Int,
    val type: ButtonType = ButtonType.PRIMARY,
    val enabled: Boolean = true
)
