package com.example.proyecto_eduardo_andres.naveHost

import kotlinx.serialization.Serializable

/**
 * todas las rutas de navegación de la aplicación.
 *
 * Se utiliza para navegar entre pantallas de forma
 *
 * Cada objeto o clase representa una pantalla distinta.
 *
 * Se emplea junto con:
 * - Navigation Compose
 * - Kotlin Serialization (@Serializable)
 * - SessionEvents para emisión global de navegación
 *
 * @author Andrés
 *
 * @param Ruta hacia la pantalla de Login.
 * @param Ruta hacia la pantalla de Crear Usuario.
 * @param Ruta hacia la pantalla de Recuperar Password.
 * @param Ruta hacia la pantalla de VideoClubOnlinePeliculas.
 * @param Ruta hacia la pantalla de VideoClubOnlineSeries.
 * @param Ruta hacia la pantalla de SearchPeliculas.
 * @param Ruta hacia la pantalla de SearchSeries.
 *
 *
 */
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
    @Serializable class InfoProyecto(val userId: String) : RouteNavigation

    @Serializable
    class AlquilerDevolverPeliculas(
        val userId: String,
        val peliculaId: String
    ) : RouteNavigation

    @Serializable
    class AlquilerDevolverSeries(
        val userId: String,
        val serieId: String
    ) : RouteNavigation

}
