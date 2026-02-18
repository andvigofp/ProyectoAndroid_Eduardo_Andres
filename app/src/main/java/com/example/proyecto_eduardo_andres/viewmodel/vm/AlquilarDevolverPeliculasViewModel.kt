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

/**
 * @author Andrés
 * @see AlquilarDevolverPeliculasViewModel
 *
 * - Obtiene el estado actual del alquiler.
 *  - Permite alquilar una película.
 *  - Permite devolver una película.
 *  - Calcula si existe multa por devolución tardía.
 *
 *  Utiliza:
 *  - StateFlow para exponer el estado a la UI.
 *  - Repository para acceder a la capa de datos.
 *
 *  @param userId Identificador del usuario que realiza la operación.
 *  @param peliculaId Identificador de la película seleccionada.
 * @param repository Implementación de IAlquilerPeliculasRepository
 * que gestiona el acceso a datos (Room / API).
 *
 *
 * @see AlquilarDevolverPeliculasUiState
 * @see IAlquilerPeliculasRepository
 * @see VideoClubOnlinePeliculasData
 * @see PeliculasDto
 * @see Date
 * @see ViewModel
 */
class AlquilarDevolverPeliculasViewModel(
    private val userId: String,
    private val peliculaId: String,
    private val repository: IAlquilerPeliculasRepository
) : ViewModel() {

    private val peliculaSeleccionada: VideoClubOnlinePeliculasData =
        PeliculasDto().peliculas.firstOrNull { it.id == peliculaId }
            ?: error("Pelicula no encontrada con nombre=$peliculaId")

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
            userId,
            peliculaSeleccionada,
            onError = { Log.e("ViewModel", "Error al cargar estado de alquiler", it) },
            onSuccess = { estado ->
                val fechaLimite = estado.fechaAlquiler?.let {
                    Date(it.time + 7 * 24 * 60 * 60 * 1000L)
                }
                _uiState.update {
                    it.copy(
                        peliculaAlquilada = estado.estaAlquilada,
                        fechaAlquiler = estado.fechaAlquiler,
                        fechaDevolucion = estado.fechaDevolucion,
                        fechaLimiteDevolucion = fechaLimite
                    )
                }
            }
        )
    }

    fun alquilarPelicula() {
        val fechaAlquiler = Date()
        val fechaLimiteDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L)

        repository.alquilarPelicula(
            userId = userId,
            pelicula = peliculaSeleccionada,
            onError = { Log.e("Alquiler", "Error al alquilar", it) },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        peliculaAlquilada = true,
                        fechaAlquiler = fechaAlquiler,
                        fechaDevolucion = null,
                        fechaLimiteDevolucion = fechaLimiteDevolucion,
                        esMulta = false
                    )
                }
            }
        )
    }

    fun devolverPelicula() {
        val fechaRealDevolucion = Date()
        val esMulta = _uiState.value.fechaLimiteDevolucion?.before(fechaRealDevolucion) ?: false

        repository.devolverPelicula(
            userId = userId,
            pelicula = peliculaSeleccionada,
            onError = { Log.e("Devolucion", "Error al devolver", it) },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        peliculaAlquilada = false,
                        fechaDevolucion = fechaRealDevolucion,
                        esMulta = esMulta
                    )
                }
            }
        )
    }
}

// Factory para pasar userId y repository al ViewModel
class AlquilarDevolverPeliculasViewModelFactory(
    private val userId: String,
    private val peliculaId: String,
    private val repository: IAlquilerPeliculasRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlquilarDevolverPeliculasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlquilarDevolverPeliculasViewModel(
                userId = userId,
                peliculaId = peliculaId,
                repository = repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}