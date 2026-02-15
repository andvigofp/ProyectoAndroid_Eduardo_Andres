package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.contactoRepository.IInfoProyectoRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.InfoProyectoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
