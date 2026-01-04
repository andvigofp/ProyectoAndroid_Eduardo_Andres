package com.example.proyecto_eduardo_andres.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.repository.alquilerSeriesRepository.IAlquilerSeriesRepository
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverSeriesData.AlquilarDevolverSeriesUiState
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverSeriesData.SeriesAlquilerDevolverData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class AlquilarDevolverSeriesViewModel (
    private val userId: Int,
    private val repository: IAlquilerSeriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AlquilarDevolverSeriesUiState(
            serie = SeriesAlquilerDevolverData().nombreSeries[0], // Selección inicial
            serieAlquilada  = false,
            fechaAlquiler = null,
            fechaDevolucion = null
        )
    )
    val uiState: StateFlow<AlquilarDevolverSeriesUiState> = _uiState.asStateFlow()

    // Función para alquilar una serie
    fun alquilarSerie() {
        val serieActual = _uiState.value.serie
        val fechaAlquiler = Date()
        val fechaDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L) // 7 días después

        // Llamamos al repositorio
        repository.alquilarSerie(
            userId = userId,
            serie = serieActual,
            onError = { error ->
                // Aquí puedes mostrar un error en UI si quieres
                Log.i("Alquiler", "Error al alquilar la serie", error)
            },
            onSuccess = {
                // Actualizamos el estado solo si fue exitoso
                _uiState.update {
                    it.copy(
                        serieAlquilada = true,
                        fechaAlquiler = fechaAlquiler,
                        fechaDevolucion = fechaDevolucion
                    )
                }
            }
        )
    }

    // Función para devolver una serie
    fun devolverSerie() {
        val serieActual = _uiState.value.serie
        val fechaDevolucion = Date()

        repository.devolverSerie(
            userId = userId,
            serie = serieActual,
            onError = { error ->
                Log.e("Devolucion", "Error al devolver la serie", error)
            },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        serieAlquilada = false,
                        fechaDevolucion = fechaDevolucion
                    )
                }
            }
        )
    }
}

// Factory para pasar userId y repository al ViewModel
class AlquilarDevolverSeriesViewModelFactory(
    private val userId: Int,
    private val repository: IAlquilerSeriesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlquilarDevolverSeriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlquilarDevolverSeriesViewModel(userId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

