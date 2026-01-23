package com.example.proyecto_eduardo_andres.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.repository.alquilerSeriesRepository.IAlquilerSeriesRepository
import com.example.proyecto_eduardo_andres.viewData.alquilerDevolverSeriesData.AlquilarDevolverSeriesUiState
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.SeriesData
import com.example.proyecto_eduardo_andres.viewData.listaSeriesData.VideoClubOnlineSeriesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class AlquilarDevolverSeriesViewModel(
    private val userId: Int,
    private val nombreSerie: Int,
    private val repository: IAlquilerSeriesRepository
) : ViewModel() {

    private val serieSeleccionada: VideoClubOnlineSeriesData =
        SeriesData().series.firstOrNull { it.nombre == nombreSerie }
            ?: error("Serie no encontrada con nombre=$nombreSerie")

    private val _uiState = MutableStateFlow(
        AlquilarDevolverSeriesUiState(
            serie = serieSeleccionada,
            serieAlquilada = false,
            fechaAlquiler = null,
            fechaDevolucion = null
        )
    )

    val uiState: StateFlow<AlquilarDevolverSeriesUiState> = _uiState.asStateFlow()

    fun alquilarSerie() {
        val fechaAlquiler = Date()
        val fechaDevolucion = Date(fechaAlquiler.time + 7 * 24 * 60 * 60 * 1000L)

        repository.alquilarSerie(
            userId = userId,
            serie = _uiState.value.serie,
            onError = { Log.e("Alquiler", "Error al alquilar", it) },
            onSuccess = {
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

    fun devolverSerie() {
        repository.devolverSerie(
            userId = userId,
            serie = _uiState.value.serie,
            onError = { Log.e("Devolucion", "Error al devolver", it) },
            onSuccess = {
                _uiState.update {
                    it.copy(
                        serieAlquilada = false,
                        fechaDevolucion = Date()
                    )
                }
            }
        )
    }
}

// Factory para pasar userId y repository al ViewModel
class AlquilarDevolverSeriesViewModelFactory(
    private val userId: Int,
    private val nombreSerie: Int,
    private val repository: IAlquilerSeriesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlquilarDevolverSeriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlquilarDevolverSeriesViewModel(
                userId = userId,
                nombreSerie = nombreSerie,
                repository = repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


