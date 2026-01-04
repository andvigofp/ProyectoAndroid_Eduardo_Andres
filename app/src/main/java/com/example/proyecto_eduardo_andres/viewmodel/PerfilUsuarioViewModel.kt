package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyecto_eduardo_andres.viewData.perfilUsuarioData.PerfilUsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PerfilUsuarioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUsuarioUiState())
    val uiState: StateFlow<PerfilUsuarioUiState> = _uiState.asStateFlow()

    fun onNombreUsuarioChange(nombre: String) {
        _uiState.update { it.copy(nombreUsuario = nombre) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun toggleEditing() {
        _uiState.update { it.copy(isEditing = !it.isEditing) }
    }

    fun guardarCambios() {
        if (_uiState.value.isModificarButtonEnabled) {
            _uiState.update { it.copy(isEditing = false) }
        }
    }
}
