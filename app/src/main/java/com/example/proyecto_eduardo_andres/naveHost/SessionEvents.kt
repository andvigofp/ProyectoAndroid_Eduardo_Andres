package com.example.proyecto_eduardo_andres.naveHost

import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Objeto singleton que gestiona eventos globales de sesión
 * dentro de la aplicación.
 *
 * Se utiliza para emitir eventos reactivos relacionados con:
 * - Navegación entre pantallas.
 * - Acceso prohibido (Forbidden / 403).
 *
 * Está basado en Kotlin Coroutines y MutableSharedFlow,
 * permitiendo comunicación desacoplada entre ViewModels
 * y la capa de UI (Jetpack Compose).
 *
 * @author Andrés
 */
object SessionEvents {

    /**
     * Flujo privado utilizado para emitir eventos de navegación.
     *
     * MutableSharedFlow permite emitir eventos
     * que pueden ser recolectados por múltiples observadores.
     */
    private val _navigation = MutableSharedFlow<RouteNavigation>()

    /**
     * Flujo público de solo lectura para observar
     * eventos de navegación desde la UI.
     */
    val navigation = _navigation

    /**
     * Flujo privado utilizado para emitir eventos
     * cuando el usuario no tiene permisos (403 Forbidden).
     */
    private val _forbidden = MutableSharedFlow<Unit>()

    /**
     * Flujo público de solo lectura para observar
     * eventos de acceso prohibido.
     */
    val forbidden = _forbidden

    /**
     * Emite un evento de acceso prohibido.
     *
     * Se utiliza normalmente cuando:
     * - El servidor devuelve un error 403.
     * - El token ha expirado.
     * - El usuario no tiene permisos.
     *
     * @throws CancellationException si la corrutina es cancelada.
     */
    suspend fun emitForbidden() {
        _forbidden.emit(Unit)
    }

    /**
     * Emite un evento de navegación.
     *
     * @param navRoute Objeto RouteNavigation que contiene
     * la información necesaria para realizar la navegación
     * (destino, argumentos, flags, etc.).
     *
     * Permite que cualquier pantalla que esté recolectando
     * el flujo navigation pueda reaccionar y navegar.
     */
    suspend fun emitNavigation(navRoute: RouteNavigation ) {
        _navigation.emit(navRoute)
    }
}