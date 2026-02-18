package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasSearchRepository.IAlquilerSearchPeliculasRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.VideoClubOnlineSearchPeliculasUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * @author Andrés
 * @see VideoClubOnlineSearchPeliculasViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de búsqueda de películas.
 * - Carga el catálogo completo desde el repositorio.
 * - Permite filtrar dinámicamente las películas según texto introducido.
 * - Mantiene una lista original y una lista filtrada.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de filtrado y presentación.
 * - Repository → Encargado de obtener el catálogo.
 *
 * Al inicializarse:
 * - Ejecuta automáticamente la carga inicial de películas.
 *
 * Utiliza:
 * - MutableStateFlow para gestionar el estado interno.
 * - StateFlow para exponer estado inmutable a la UI.
 * - update {} para mantener inmutabilidad.
 *
 * En Jetpack Compose:
 * - La UI observa uiState.
 * - Cuando cambia query o peliculasFiltradas,
 *   la pantalla se recompone automáticamente.
 *
 * Nota:
 * Se utiliza Context para obtener el nombre real
 * de la película desde recursos (string resources).
 *
 * @param repository Repositorio encargado de obtener
 * el catálogo de películas.
 * @param context Contexto necesario para resolver
 * recursos de tipo string.
 *
 * @see VideoClubOnlineSearchPeliculasUiState
 * @see IAlquilerSearchPeliculasRepository
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 * @see Context
 */
class VideoClubOnlineSearchPeliculasViewModel(
    private val repository: IAlquilerSearchPeliculasRepository,
    private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoClubOnlineSearchPeliculasUiState())
    val uiState: StateFlow<VideoClubOnlineSearchPeliculasUiState> =
        _uiState.asStateFlow()

    init {
        cargarPeliculas()
    }

    private fun cargarPeliculas() {
        repository.obtenerPeliculasSearch(
            onError = {
                _uiState.update { it.copy(isLoading = false) }
            },
            onSuccess = { peliculas ->
                _uiState.update {
                    it.copy(
                        peliculas = peliculas,
                        peliculasFiltradas = peliculas,
                        isLoading = false
                    )
                }
            }
        )
    }

    fun onQueryChange(query: String) {
        _uiState.update { state ->

            val filtradas = state.peliculas.filter { pelicula ->
                val nombreReal = context.getString(pelicula.nombre)
                nombreReal.contains(query, ignoreCase = true)
            }

            state.copy(
                query = query,
                peliculasFiltradas = filtradas
            )
        }
    }
}



class VideoClubOnlineSearchPeliculasViewModelFactory(
    private val repository: IAlquilerSearchPeliculasRepository,
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoClubOnlineSearchPeliculasViewModel::class.java)) {
            return VideoClubOnlineSearchPeliculasViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}