package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.contactoRepository.IInfoProyectoRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.InfoProyectoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author Andrés
 * @see InfoProyectoViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de información del proyecto.
 * - Solicita al repositorio los datos descriptivos del proyecto.
 * - Expone el estado de forma reactiva mediante StateFlow.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación.
 * - Repository → Se encarga de obtener los datos.
 *
 * Al inicializarse:
 * - Ejecuta automáticamente la carga de información.
 * - Actualiza el estado cuando recibe los datos.
 *
 * Utiliza:
 * - MutableStateFlow para modificar el estado interno.
 * - StateFlow para exponer estado inmutable a la UI.
 * - asStateFlow() para convertir MutableStateFlow en StateFlow.
 *
 *
 * @param repository Repositorio encargado de proporcionar
 * la información del proyecto.
 *
 * @see InfoProyectoUiState
 * @see IInfoProyectoRepository
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 * @see asStateFlow
 */
class InfoProyectoViewModel(
    private val repository: IInfoProyectoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(InfoProyectoUiState())
    val uiState: StateFlow<InfoProyectoUiState> = _uiState.asStateFlow()

    init {
        cargarInfo()
    }

    private fun cargarInfo() {
        repository.obtenerInfo(
            onSuccess = { dto ->
                _uiState.value = InfoProyectoUiState(
                    titulo = dto.titulo,
                    descripcion = dto.descripcion,
                    integrantes = dto.integrantes
                )
            },
            onError = { }
        )
    }
}

class InfoProyectoViewModelFactory(
    private val repository: IInfoProyectoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoProyectoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InfoProyectoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}