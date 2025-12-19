package com.example.proyecto_eduardo_andres.naveHost

import com.example.proyecto_eduardo_andres.R
import kotlinx.serialization.Serializable

sealed class Screen(val route: String) {

    object Login : Screen("login")
    object CrearUsuario : Screen("crear_usuario")
    object RecuperarPassword : Screen("recuperar_password")

    object VideoClubPeliculas : Screen("videoclub_peliculas")
    object VideoClubSeries : Screen("videoclub_series")

    object SearchPeliculas : Screen("search_peliculas")
    object SearchSeries : Screen("search_series")

    object QR : Screen("qr")
    object Camara : Screen("camara")

    object PerfilUsuario : Screen("perfil_usuario")

    object AlquilerDevolverPeliculas : Screen("alquiler_peliculas")
    object AlquilerDevolverSeries : Screen("alquiler_series")

    object Menu : Screen("menu")
}
