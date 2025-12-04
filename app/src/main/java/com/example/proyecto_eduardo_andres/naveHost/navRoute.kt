package com.example.proyecto_eduardo_andres.naveHost

import com.example.proyecto_eduardo_andres.R
import kotlinx.serialization.Serializable

sealed class Screen(val routeId: Int) {
    @Serializable
    class Login(routeId: Int) : Screen(routeId)

    @Serializable
    class CrearUsuario(routeId: Int) : Screen(routeId)

    @Serializable
    class RecuperarPassword(routeId: Int) : Screen(routeId)

    @Serializable
    class VideoClubOnlinePeliculas(routeId: Int) : Screen(routeId)

    @Serializable
    class VideoClubOnlineSeries(routeId: Int) : Screen(routeId)

    @Serializable
    class SearchPeliculas(routeId: Int) : Screen(routeId)

    @Serializable
    class SearchSeries(routeId: Int) : Screen(routeId)

    @Serializable
    class QR(routeId: Int) : Screen(routeId)

    @Serializable
    class Camara(routeId: Int) : Screen(routeId)

    @Serializable
    class Menu(routeId: Int) : Screen(routeId)

    @Serializable
    class PerfilUsuario(routeId: Int) : Screen(routeId)
}

object AppScreens {
    val Login = Screen.Login(R.string.screen_login)
    val CrearUsuario = Screen.CrearUsuario(R.string.screen_crear_usuario)
    val RecuperarPassword = Screen.RecuperarPassword(R.string.screen_recuperar_password)
    val VideoClubPeliculas = Screen.VideoClubOnlinePeliculas(R.string.screen_videoclub_peliculas)
    val VideoClubSeries = Screen.VideoClubOnlineSeries(R.string.screen_videoclub_series)
    val SearchPeliculas = Screen.SearchPeliculas(R.string.screen_search_peliculas)
    val SearchSeries = Screen.SearchSeries(R.string.screen_search_series)
    val QR = Screen.QR(R.string.screen_qr)
    val Camara = Screen.Camara(R.string.screen_camara)
    val Menu = Screen.Menu(R.string.screen_menu)
    val PerfilUsuario = Screen.PerfilUsuario(R.string.screen_perfil_usuario)
}
