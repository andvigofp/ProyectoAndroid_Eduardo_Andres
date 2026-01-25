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
    class VideoClubPeliculas(val id: Int) : RouteNavigation

    @Serializable
    class VideoClubSeries(val id: Int) : RouteNavigation

    @Serializable
    class SearchPeliculas(val id: Int) : RouteNavigation

    @Serializable
    class SearchSeries(val id: Int) : RouteNavigation

    @Serializable
    class QR(val id: Int) : RouteNavigation

    @Serializable
    class Camara(val id: Int) : RouteNavigation

    @Serializable
    class PerfilUsuario(val id: Int) : RouteNavigation

    @Serializable
    class AlquilerDevolverPeliculas(
        val id: Int,
        val nombrePelicula: Int
    ) : RouteNavigation

    @Serializable
    class AlquilerDevolverSeries(
        val userId: Int,
        val nombreSerie: Int
    ) : RouteNavigation

}
