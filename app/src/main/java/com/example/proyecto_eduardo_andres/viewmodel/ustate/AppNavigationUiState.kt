package com.example.proyecto_eduardo_andres.viewmodel.ustate

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation

/**
 * @author Andrés
 * @see AppNavigationUiState
 *
 * @param UIState para la navegación de la aplicación.
 * @param isCheckingSession Indica si se está revisando la sesión.
 * @param currentUser Usuario actual.
 * @param initialRoute Ruta inicial de la navegación.
 */
data class AppNavigationUiState(
    val isCheckingSession: Boolean = true,
    val currentUser: UserDTO? = null,
    val initialRoute: RouteNavigation = RouteNavigation.Login
)

