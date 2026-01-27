package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.myComponents.componeneteCrearUsuario.CrearUsuarioUiState
import com.example.proyecto_eduardo_andres.repository.crearUsuario.CrearUsuarioRepositoryInMemory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CrearUsuarioViewModel(
    private val repositoryInMemory: CrearUsuarioRepositoryInMemory
) : ViewModel() {
    private val _uiState = MutableStateFlow(CrearUsuarioUiState())
    val uiState: StateFlow<CrearUsuarioUiState> = _uiState.asStateFlow()

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(nombre = newName) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        _uiState.update { it.copy(repeatPassword = newRepeatPassword) }
    }

    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onRepeatEmailChange(newRepeatEmail: String) {
        _uiState.update { it.copy(repeatEmail = newRepeatEmail) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun toggleRepeatPasswordVisibility() {
        _uiState.update { it.copy(repeatPasswordVisible = !it.repeatPasswordVisible) }
    }

    fun crearUsuario(
        onSuccess: (UserDTO) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val state = _uiState.value

        // Validaciones de formulario
        if (state.password != state.repeatPassword) {
            onError(Throwable("Las contraseñas no coinciden"))
            return
        }

        if (state.email != state.repeatEmail) {
            onError(Throwable("Los emails no coinciden"))
            return
        }

        repositoryInMemory.crearUsuario(
            nombre = state.nombre,
            email = state.email,
            password = state.password,
            onSuccess = onSuccess,
            onError = onError
        )
    }
}

class CrearUsuarioViewModelFactory(
    private val repositoryInMemory: CrearUsuarioRepositoryInMemory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrearUsuarioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CrearUsuarioViewModel(repositoryInMemory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
