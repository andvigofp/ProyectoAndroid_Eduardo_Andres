package com.example.proyecto_eduardo_andres.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData.AlquilarDevolverPeliculasUiState
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.PeliculasData
import com.example.proyecto_eduardo_andres.viewData.listaPeliculasData.VideoClubOnlinePeliculasData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class AlquilarDevolverPeliculasViewModel(
    private val userId: Int,
    private val nombrePelicula: Int,
    private val repository: IAlquilerPeliculasRepository
) : ViewModel() {

    private val peliculaSeleccionada: VideoClubOnlinePeliculasData =
        PeliculasData().peliculas.firstOrNull { it.nombre == nombrePelicula }
            ?: error("Pelicula no encontrada con nombre=$nombrePelicula")

    private val _uiState = MutableStateFlow(
        AlquilarDevolverPeliculasUiState(
            pelicula = peliculaSeleccionada,
            peliculaAlquilada = false,
            fechaAlquiler = null,
            fechaDevolucion = null
        )
    )
    val uiState: StateFlow<AlquilarDevolverPeliculasUiState> = _uiState.asStateFlow()

    // Función para alquilar una película
    fun alquilarPelicula() {
        val fechaAlquiler = Date()
        val fechaDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L) // 7 días después

        // Llamamos al repositorio
        repository.alquilarPelicula(
            userId = userId,
            pelicula = _uiState.value.pelicula,
            onError = { Log.e("Alquiler", "Error al alquilar", it) },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        peliculaAlquilada = true,
                        fechaAlquiler = fechaAlquiler,
                        fechaDevolucion = fechaDevolucion
                    )
                }
            }
        )
    }

    // Función para devolver una película
    fun devolverPelicula() {
        repository.devolverPelicula(
            userId = userId,
            pelicula = _uiState.value.pelicula,
            onError = { Log.e("Devolucion", "Error al devolver", it) },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        peliculaAlquilada  = false,
                        fechaDevolucion = Date()
                    )
                }
            }
        )
    }
}

// Factory para pasar userId y repository al ViewModel
class AlquilarDevolverPeliculasViewModelFactory(
    private val userId: Int,
    private val nombrePelicula: Int,
    private val repository: IAlquilerPeliculasRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlquilarDevolverPeliculasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlquilarDevolverPeliculasViewModel(
                userId = userId,
                nombrePelicula = nombrePelicula,
                repository = repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
