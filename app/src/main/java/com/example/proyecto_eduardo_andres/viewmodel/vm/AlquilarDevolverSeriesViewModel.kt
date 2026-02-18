package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.IAlquilerSeriesRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.AlquilarDevolverSeriesUiState
import com.example.proyecto_eduardo_andres.modelo.SeriesDto
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

/**
 * @author Eduardo
 * @see AlquilarDevolverSeriesViewModel
 *
 * Esta clase:
 * - Obtiene el estado actual del alquiler de una serie.
 * - Permite alquilar una serie.
 * - Permite devolver una serie.
 * - Calcula si existe multa por devolución tardía.
 *
 * Utiliza:
 * - StateFlow para exponer el estado reactivo a la UI.
 * - Un repositorio para acceder a la capa de datos (Room / API).
 *
 * @param userId Identificador del usuario que realiza la operación.
 * @param serieId Identificador de la serie seleccionada.
 * @param repository Implementación de IAlquilerSeriesRepository
 * encargada de gestionar el acceso a datos.
 *
 * @see AlquilarDevolverSeriesUiState
 * @see IAlquilerSeriesRepository
 * @see VideoClubOnlineSeriesData
 */
class AlquilarDevolverSeriesViewModel(
    private val userId: String,
    private val serieId: String,
    private val repository: IAlquilerSeriesRepository
) : ViewModel() {

    private val serieSeleccionada: VideoClubOnlineSeriesData =
        SeriesDto().series.firstOrNull { it.id == serieId }
            ?: error("Serie no encontrada con id=$serieId")

    private val _uiState = MutableStateFlow(
        AlquilarDevolverSeriesUiState(
            serie = serieSeleccionada
        )
    )

    val uiState: StateFlow<AlquilarDevolverSeriesUiState> = _uiState.asStateFlow()

    init {
        cargarDatosIniciales()
    }

    fun cargarDatosIniciales() {
        repository.obtenerEstadoAlquiler(
            userId,
            serieSeleccionada,
            onError = { Log.e("ViewModel", "Error al cargar estado de alquiler", it) },
            onSuccess = { estado ->
                val fechaLimite = estado.fechaAlquiler?.let {
                    Date(it.time + 7 * 24 * 60 * 60 * 1000L)
                }
                _uiState.update {
                    it.copy(
                        serieAlquilada = estado.estaAlquilada,
                        fechaAlquiler = estado.fechaAlquiler,
                        fechaDevolucion = estado.fechaDevolucion,
                        fechaLimiteDevolucion = fechaLimite
                    )
                }
            }
        )
    }

    fun alquilarSerie() {
        val fechaAlquiler = Date()
        val fechaLimiteDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L)

        repository.alquilarSerie(
            userId = userId,
            serie = serieSeleccionada,
            onError = { Log.e("Alquiler", "Error al alquilar", it) },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        serieAlquilada = true,
                        fechaAlquiler = fechaAlquiler,
                        fechaDevolucion = null,
                        fechaLimiteDevolucion = fechaLimiteDevolucion,
                        esMulta = false
                    )
                }
            }
        )
    }

    fun devolverSerie() {
        val fechaRealDevolucion = Date()
        val esMulta = _uiState.value.fechaLimiteDevolucion?.before(fechaRealDevolucion) ?: false

        repository.devolverSerie(
            userId = userId,
            serie = serieSeleccionada,
            onError = { Log.e("Devolucion", "Error al devolver", it) },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        serieAlquilada = false,
                        fechaDevolucion = fechaRealDevolucion,
                        esMulta = esMulta
                    )
                }
            }
        )
    }
}

// Factory para pasar userId y repository al ViewModel
class AlquilarDevolverSeriesViewModelFactory(
    private val userId: String,
    private val serieId: String,
    private val repository: IAlquilerSeriesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlquilarDevolverSeriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlquilarDevolverSeriesViewModel(
                userId = userId,
                serieId = serieId,
                repository = repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
