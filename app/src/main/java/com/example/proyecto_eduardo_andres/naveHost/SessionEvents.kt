package com.example.proyecto_eduardo_andres.naveHost

import kotlinx.coroutines.flow.MutableSharedFlow

object SessionEvents {
    private val _navigation = MutableSharedFlow<RouteNavigation>()
    val navigation = _navigation


    suspend fun emitNavigation(navRoute: RouteNavigation ) {
        _navigation.emit(navRoute)
    }
}