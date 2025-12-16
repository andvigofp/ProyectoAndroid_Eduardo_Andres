package com.example.proyecto_eduardo_andres.naveHost

import com.example.proyecto_eduardo_andres.R
import kotlinx.serialization.Serializable

sealed class Screen(val routeId: Int) {
    @Serializable
    class Login(val routeId: Int)

    @Serializable
    class CrearUsuario(val routeId: Int)

    @Serializable
    class RecuperarPassword(val routeId: Int)

    @Serializable
    class VideoClubOnlinePeliculas(val routeId: Int)

    @Serializable
    class VideoClubOnlineSeries(val routeId: Int)

    @Serializable
    class SearchPeliculas(val routeId: Int)

    @Serializable
    class SearchSeries(val routeId: Int)

    @Serializable
    class QR(val routeId: Int)

    @Serializable
    class Camara(val routeId: Int)

    @Serializable
    class Menu(val routeId: Int)

    @Serializable
    class PerfilUsuario(val routeId: Int)
    @Serializable
    class AlquilerDevolverPeliculas(val routeId: Int)

    @Serializable
    class AlquilerDevolverSeries(val routeId: Int)
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
    val AlquilerDevolverSerie = Screen.AlquilerDevolverSeries(R.string.screen_alquilar_devolver_series)
    val AlquilerDevolverPeliculas = Screen.AlquilerDevolverPeliculas(R.string.screen_alquilar_devolver_peliculas)
}
