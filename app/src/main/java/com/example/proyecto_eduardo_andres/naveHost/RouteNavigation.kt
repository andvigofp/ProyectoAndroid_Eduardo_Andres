package com.example.proyecto_eduardo_andres.naveHost

import kotlinx.serialization.Serializable

sealed interface RouteNavigation {

    @Serializable
    object Login : RouteNavigation

    @Serializable
    object CrearUsuario : RouteNavigation

    @Serializable
    object RecuperarPassword : RouteNavigation

    @Serializable
    object VideoClubPeliculas : RouteNavigation

    @Serializable
    object VideoClubSeries : RouteNavigation

    @Serializable
    object SearchPeliculas : RouteNavigation

    @Serializable
    object SearchSeries : RouteNavigation

    @Serializable
    object QR : RouteNavigation

    @Serializable
    object Camara : RouteNavigation

    @Serializable
    object PerfilUsuario : RouteNavigation

    @Serializable
    object AlquilerDevolverPeliculas : RouteNavigation

    @Serializable
    object AlquilerDevolverSeries : RouteNavigation
}
