package com.example.proyecto_eduardo_andres.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.repository.AlquilerPeliculasRepository.IAlquilerPeliculasRepository
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData.AlquilarDevolverPeliculasUiState
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData.PeliculasAlquilarDevolverData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class AlquilarDevolverPeliculasViewModel(
    private val userId: Int,
    private val repository: IAlquilerPeliculasRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AlquilarDevolverPeliculasUiState(
            pelicula = PeliculasAlquilarDevolverData().nombrePeliculas[0], // Selección inicial
            peliculaAlquilada = false,
            fechaAlquiler = null,
            fechaDevolucion = null
        )
    )
    val uiState: StateFlow<AlquilarDevolverPeliculasUiState> = _uiState.asStateFlow()

    // Función para alquilar una película
    fun alquilarPelicula() {
        val peliculaActual = _uiState.value.pelicula
        val fechaAlquiler = Date()
        val fechaDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L) // 7 días después

        // Llamamos al repositorio
        repository.alquilarPelicula(
            userId = userId,
            pelicula = peliculaActual,
            onError = { error ->
                // Aquí puedes mostrar un error en UI si quieres
                Log.i("Alquiler", "Error al alquilar la película", error)
            },
            onSuccess = {
                // Actualizamos el estado solo si fue exitoso
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
        val peliculaActual = _uiState.value.pelicula
        val fechaDevolucion = Date()

        repository.devolverPelicula(
            userId = userId,
            pelicula = peliculaActual,
            onError = { error ->
                Log.e("Devolucion", "Error al devolver la película", error)
            },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        peliculaAlquilada = false,
                        fechaDevolucion = fechaDevolucion
                    )
                }
            }
        )
    }
}

// Factory para pasar userId y repository al ViewModel
class AlquilarDevolverPeliculasViewModelFactory(
    private val userId: Int,
    private val repository: IAlquilerPeliculasRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlquilarDevolverPeliculasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlquilarDevolverPeliculasViewModel(userId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
