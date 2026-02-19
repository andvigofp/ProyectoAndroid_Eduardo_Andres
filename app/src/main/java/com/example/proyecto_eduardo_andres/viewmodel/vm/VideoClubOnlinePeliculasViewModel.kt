package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.data.repository.peliculasRepository.IPeliculasRepository
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlinePeliculasData
import com.example.proyecto_eduardo_andres.viewmodel.ustate.VideoClubOnlinePeliculasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author Andrés
 * @see VideoClubOnlinePeliculasViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla principal de películas.
 * - Carga el catálogo completo desde el repositorio.
 * - Agrupa las películas por categoría.
 * - Permite filtrar películas por categoría.
 * - Controla el estado de carga (loading).
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación.
 * - Repository → Encargado de obtener las películas.
 *
 * Al inicializarse:
 * - Ejecuta automáticamente la carga del catálogo.
 *
 * Utiliza:
 * - MutableStateFlow para modificar el estado interno.
 * - StateFlow para exponer estado inmutable a la UI.
 * - update {} para mantener inmutabilidad.
 *
 * En Jetpack Compose:
 * - La UI observa uiState.
 * - Cuando cambian las películas o el estado de carga,
 *   la pantalla se recompone automáticamente.
 *
 * @param repository Repositorio encargado de obtener
 * el catálogo de películas.
 * @param sessionEvents Objeto global opcional para
 * gestionar eventos de sesión.
 *
 * @see VideoClubOnlinePeliculasUiState
 * @see IPeliculasRepository
 * @see SessionEvents
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 */
class VideoClubOnlinePeliculasViewModel(
    private val repository: IPeliculasRepository,
    private val sessionEvents: SessionEvents = SessionEvents
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlinePeliculasUiState())
    val uiState: StateFlow<VideoClubOnlinePeliculasUiState> = _uiState.asStateFlow()

    init {
        cargarPeliculas()
    }

    private fun cargarPeliculas() {

        _uiState.update { it.copy(isLoading = true) }

        repository.obtenerPeliculas(
            onError = {
                _uiState.update { it.copy(isLoading = false) }
            },
            onSuccess = { peliculas ->

                val agrupadas = peliculas.groupBy { it.categoria }

                _uiState.update {
                    it.copy(
                        peliculas = peliculas,
                        peliculasPorCategoria = agrupadas,
                        isLoading = false
                    )
                }
            }
        )
    }


    fun filtrarPorCategoria(categoria: Int) {
        val filtradas = _uiState.value.peliculas
            .filter { it.categoria == categoria }

        _uiState.update {
            it.copy(
                peliculasPorCategoria = mapOf(categoria to filtradas)
            )
        }
    }
}

class VideoClubOnlinePeliculasViewModelFactory(
    private val repository: IPeliculasRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoClubOnlinePeliculasViewModel::class.java)) {
            return VideoClubOnlinePeliculasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
