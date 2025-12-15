package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData.AlquilarDevolverPeliculasUiState
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData.PeliculasAlquilarDevolverData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class AlquilarDevolverPeliculasViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        AlquilarDevolverPeliculasUiState(
            pelicula = PeliculasAlquilarDevolverData().nombrePeliculas[0], // Aquí seleccionas la película "La vida es bella"
            peliculaAlquilada = false,
            fechaAlquiler = null, // No alquilada inicialmente
            fechaDevolucion = null // No devuelta inicialmente
        )
    )
    val uiState: StateFlow<AlquilarDevolverPeliculasUiState> = _uiState.asStateFlow()

    // Función para alquilar una película
    fun alquilarPelicula() {
        val fechaAlquiler = Date()
        val fechaDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L) // Ejemplo: 7 días después

        _uiState.update {
            it.copy(
                peliculaAlquilada = true,
                fechaAlquiler = fechaAlquiler,
                fechaDevolucion = fechaDevolucion
            )
        }
    }

    // Función para devolver una película
    fun devolverPelicula() {
        val fechaDevolucion = Date()
        _uiState.update {
            it.copy(
                peliculaAlquilada = false,
                fechaDevolucion = fechaDevolucion
            )
        }
    }
}
