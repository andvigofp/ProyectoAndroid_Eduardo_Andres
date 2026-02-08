package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation

data class AppNavigationUiState(
    val isCheckingSession: Boolean = true,
    val currentUser: UserDTO? = null,
    val initialRoute: RouteNavigation = RouteNavigation.Login
)

