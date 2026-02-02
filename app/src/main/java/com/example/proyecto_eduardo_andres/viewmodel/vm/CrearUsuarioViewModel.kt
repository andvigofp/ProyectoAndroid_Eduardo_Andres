package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.viewmodel.ustate.CrearUsuarioUiState
import com.example.proyecto_eduardo_andres.data.repository.crearUsuario.CrearUsuarioRepositoryInMemory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CrearUsuarioViewModel(
    private val repositoryInMemory: com.example.proyecto_eduardo_andres.data.repository.crearUsuario.CrearUsuarioRepositoryInMemory
) : ViewModel() {

    private val _uiState = MutableStateFlow(CrearUsuarioUiState())
    val uiState: StateFlow<CrearUsuarioUiState> = _uiState.asStateFlow()

    var showCrearUsuarioDialog by mutableStateOf(false)
        private set

    var crearUsuarioMessage by mutableStateOf("")
        private set

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

    fun clearFields() {
        _uiState.value = CrearUsuarioUiState() // Resetea todos los campos
    }


    fun showDialog(message: String) {
        crearUsuarioMessage = message
        showCrearUsuarioDialog = true
    }

    fun dismissDialog() {
        showCrearUsuarioDialog = false
    }



    fun crearUsuario(
        onSuccess: (UserDTO) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val state = _uiState.value

        // Validaciones
        if (state.password != state.repeatPassword) {
            showDialog("Las contraseñas no coinciden")
            return
        }

        if (state.email != state.repeatEmail) {
            showDialog("Los emails no coinciden")
            return
        }

        repositoryInMemory.crearUsuario(
            nombre = state.nombre,
            email = state.email,
            password = state.password,
            onSuccess = { user ->
                showDialog("Usuario creado correctamente")
                clearFields()  // Limpiamos los campos
                onSuccess(user) // Esto se usará solo para navegar
            },
            onError = { error ->
                showDialog(error.message ?: "Error desconocido")
                clearFields()  // También limpiamos los campos si falla
                onError(error) // Solo mostrar error, no navegar
            }
        )
    }
}


    class CrearUsuarioViewModelFactory(
        private val repositoryInMemory: com.example.proyecto_eduardo_andres.data.repository.crearUsuario.CrearUsuarioRepositoryInMemory
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CrearUsuarioViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CrearUsuarioViewModel(repositoryInMemory) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
