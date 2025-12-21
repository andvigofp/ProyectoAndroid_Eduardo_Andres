package com.example.proyecto_eduardo_andres.naveHost

import kotlinx.coroutines.flow.MutableSharedFlow

object SessionEvents {
    private val _navigation = MutableSharedFlow<RouteNavigation>()
    val navigation = _navigation

    private val _forbidden = MutableSharedFlow<Unit>()
    val forbidden = _forbidden


    suspend fun emitForbidden() {
        _forbidden.emit(Unit)
    }

    suspend fun emitNavigation(navRoute: RouteNavigation ) {
        _navigation.emit(navRoute)
    }
}