package com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository


import com.example.proyecto_eduardo_andres.modelo.EstadoAlquilerDto
import com.example.proyecto_eduardo_andres.modelo.PeliculasDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import java.util.Date

class AlquilerPeliculasRepositoryInMemory :
    com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository {

    // Mapa que guarda por usuario y por película su estado
    private val alquileres: MutableMap<Int, MutableMap<Int, EstadoAlquilerDto>> = mutableMapOf()

    override fun alquilarPelicula(
        userId: Int,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val fechaAlquiler = Date()
            val fechaDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L)

            val peliculasUsuario = alquileres.getOrPut(userId) { mutableMapOf() }
            peliculasUsuario[pelicula.nombre] = EstadoAlquilerDto(
                estaAlquilada = true,
                fechaAlquiler = fechaAlquiler,
                fechaDevolucion = fechaDevolucion
            )
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun devolverPelicula(
        userId: Int,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        try {
            val peliculasUsuario = alquileres[userId]
            if (peliculasUsuario != null && peliculasUsuario.containsKey(pelicula.nombre)) {
                peliculasUsuario[pelicula.nombre] = peliculasUsuario[pelicula.nombre]!!.copy(
                    estaAlquilada = false,
                    fechaDevolucion = Date()
                )
            }
            onSuccess()
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun obtenerEstadoAlquiler(
        userId: Int,
        pelicula: VideoClubOnlinePeliculasData,
        onError: (Throwable) -> Unit,
        onSuccess: (EstadoAlquilerDto) -> Unit
    ) {
        try {
            val peliculasUsuario = alquileres[userId]
            val estado = peliculasUsuario?.get(pelicula.nombre)
                ?: EstadoAlquilerDto(false, null, null)
            onSuccess(estado)
        } catch (e: Throwable) {
            onError(e)
        }
    }

    override fun obtenerPeliculasAlquiladas(
        userId: Int,
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlinePeliculasData>) -> Unit
    ) {
        try {
            val peliculasUsuario = alquileres[userId] ?: emptyMap()
            val todasLasPeliculas = PeliculasDto().peliculas
            
            // Filtrar las películas que están alquiladas (estaAlquilada = true)
            val peliculasAlquiladas = todasLasPeliculas.filter { pelicula ->
                val estado = peliculasUsuario[pelicula.nombre]
                estado?.estaAlquilada == true
            }
            
            onSuccess(peliculasAlquiladas)
        } catch (e: Throwable) {
            onError(e)
        }
    }
}