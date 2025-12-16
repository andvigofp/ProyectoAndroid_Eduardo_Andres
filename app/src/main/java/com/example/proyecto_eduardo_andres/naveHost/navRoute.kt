package com.example.proyecto_eduardo_andres.naveHost

import com.example.proyecto_eduardo_andres.R
import kotlinx.serialization.Serializable

sealed class Screen(val routeId: Int) {

    @Serializable
    object Login : Screen(R.string.screen_login)

    @Serializable
    object CrearUsuario : Screen(R.string.screen_crear_usuario)

    @Serializable
    object RecuperarPassword : Screen(R.string.screen_recuperar_password)

    @Serializable
    object VideoClubPeliculas : Screen(R.string.screen_videoclub_peliculas)

    @Serializable
    object VideoClubSeries : Screen(R.string.screen_videoclub_series)

    @Serializable
    object SearchPeliculas : Screen(R.string.screen_search_peliculas)

    @Serializable
    object SearchSeries : Screen(R.string.screen_search_series)

    @Serializable
    object QR : Screen(R.string.screen_qr)

    @Serializable
    object Camara : Screen(R.string.screen_camara)

    @Serializable
    object PerfilUsuario : Screen(R.string.screen_perfil_usuario)

    @Serializable
    object AlquilerDevolverPeliculas : Screen(R.string.screen_alquilar_devolver_peliculas)

    @Serializable
    object AlquilerDevolverSeries : Screen(R.string.screen_alquilar_devolver_series)

    @Serializable
    object Menu : Screen(R.string.menu)

}
