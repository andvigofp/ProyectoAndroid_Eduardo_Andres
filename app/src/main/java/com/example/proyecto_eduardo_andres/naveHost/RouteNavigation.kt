package com.example.proyecto_eduardo_andres.naveHost

import kotlinx.serialization.Serializable

sealed interface RouteNavigation {

    @Serializable object Login : RouteNavigation
    @Serializable object CrearUsuario : RouteNavigation
    @Serializable object RecuperarPassword : RouteNavigation

    @Serializable class VideoClubPeliculas(val userId: String) : RouteNavigation
    @Serializable class VideoClubSeries(val userId: String) : RouteNavigation
    @Serializable class SearchPeliculas(val userId: String) : RouteNavigation
    @Serializable class SearchSeries(val userId: String) : RouteNavigation
    @Serializable class QR(val userId: String) : RouteNavigation
    @Serializable class Camara(val userId: String) : RouteNavigation
    @Serializable class PerfilUsuario(val userId: String) : RouteNavigation
    @Serializable class PerfilSeries(val userId: String) : RouteNavigation

    @Serializable
    class AlquilerDevolverPeliculas(
        val userId: String,
        val nombrePelicula: Int
    ) : RouteNavigation

    @Serializable
    class AlquilerDevolverSeries(
        val userId: String,
        val nombreSerie: Int
    ) : RouteNavigation

}
