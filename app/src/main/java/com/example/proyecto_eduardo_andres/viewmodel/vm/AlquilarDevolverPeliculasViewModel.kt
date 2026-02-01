package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.AlquilarDevolverPeliculasUiState
import com.example.proyecto_eduardo_andres.modelo.PeliculasDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class AlquilarDevolverPeliculasViewModel(
    private val userId: String,
    private val nombrePelicula: Int,
    private val repository: IAlquilerPeliculasRepository
) : ViewModel() {

    private val peliculaSeleccionada: VideoClubOnlinePeliculasData =
        PeliculasDto().peliculas.firstOrNull { it.nombre == nombrePelicula }
            ?: error("Pelicula no encontrada con nombre=$nombrePelicula")

    private val _uiState = MutableStateFlow(
        AlquilarDevolverPeliculasUiState(
            pelicula = peliculaSeleccionada
        )
    )
    val uiState: StateFlow<AlquilarDevolverPeliculasUiState> = _uiState.asStateFlow()

    init {
        cargarDatosIniciales()
    }

    fun cargarDatosIniciales() {
        repository.obtenerEstadoAlquiler(
            userId.toString(),
            peliculaSeleccionada,
            onError = { Log.e("ViewModel", "Error al cargar estado de alquiler", it) },
            onSuccess = { estado ->
                _uiState.update {
                    it.copy(
                        peliculaAlquilada = estado.estaAlquilada,
                        fechaAlquiler = estado.fechaAlquiler,
                        fechaDevolucion = estado.fechaDevolucion
                    )
                }
            }
        )
    }

    fun alquilarPelicula() {
        val fechaAlquiler = Date()
        val fechaDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L)

        repository.alquilarPelicula(
            userId = userId.toString(),
            pelicula = peliculaSeleccionada,
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

    fun devolverPelicula() {
        repository.devolverPelicula(
            userId = userId.toString(),
            pelicula = peliculaSeleccionada,
            onError = { Log.e("Devolucion", "Error al devolver", it) },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        peliculaAlquilada = false,
                        fechaDevolucion = Date()
                    )
                }
            }
        )
    }
}



// Factory para pasar userId y repository al ViewModel
class AlquilarDevolverPeliculasViewModelFactory(
    private val userId: String,
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
