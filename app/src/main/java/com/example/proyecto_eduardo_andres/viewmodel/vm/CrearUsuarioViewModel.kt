package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.viewmodel.ustate.CrearUsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.data.repository.crearUsuario.CrearUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.crearUsuario.ICrearUsuarioRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Eduardo
 * @see CrearUsuarioViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de creación de usuario.
 * - Controla los campos del formulario (nombre, email, contraseña).
 * - Maneja la visibilidad de contraseñas.
 * - Valida datos antes de enviarlos al repositorio.
 * - Controla la visualización de diálogos informativos.
 * - Ejecuta la creación de usuario delegando la lógica al repositorio.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación y validación.
 * - Repository → Encargado de la lógica de acceso a datos.
 *
 * Utiliza:
 * - StateFlow para exponer el estado reactivo del formulario.
 * - MutableStateFlow para modificar el estado internamente.
 * - mutableStateOf para controlar estados simples de UI
 *   como la visibilidad del diálogo.
 * - viewModelScope para manejar corrutinas seguras.
 *
 *
 * @param repository Repositorio encargado de crear
 * el usuario en base de datos.
 *
 * @see CrearUsuarioUiState
 * @see ICrearUsuarioRepository
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 * @see mutableStateOf
 * @see viewModelScope
 */
class CrearUsuarioViewModel(
    private val repository: ICrearUsuarioRepository
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


    /**
     * Muestra el diálogo de creación con un título opcional.
     * @param message Mensaje a mostrar
     * @param title Título del diálogo (por defecto "Información")
     */
    fun showDialog(message: String, title: String = "Información") {
        dialogTitle = title
        crearUsuarioMessage = message
        showCrearUsuarioDialog = true
        // Cancel any previous auto-dismiss job
        autoDismissJob?.cancel()
        // Auto-dismiss after 2.5s
        autoDismissJob = viewModelScope.launch {
            delay(2500L)
            showCrearUsuarioDialog = false
            // clear reference when done
            autoDismissJob = null
        }
    }

    fun dismissDialog() {
        showCrearUsuarioDialog = false
        // Cancel pending auto-dismiss if user dismissed manually
        autoDismissJob?.cancel()
        autoDismissJob = null
    }

    var dialogTitle by mutableStateOf("Información")
        private set

    private var autoDismissJob: Job? = null


    fun crearUsuario(
        onSuccess: (UserDTO) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val state = _uiState.value

        // Validaciones
        if (state.password != state.repeatPassword) {
            showDialog("Las contraseñas no coinciden", title = "Error")
            return
        }

        if (state.email != state.repeatEmail) {
            showDialog("Los emails no coinciden", title = "Error")
            return
        }

        repository.crearUsuario(
            nombre = state.nombre,
            email = state.email,
            password = state.password,
            onSuccess = { user ->
                showDialog("Usuario creado correctamente", title = "¡Éxito!")
                clearFields()  // Limpiamos los campos
                onSuccess(user) // Esto se usará solo para navegar
            },
            onError = { error ->
                showDialog(error.message ?: "Error desconocido", title = "Error")
                clearFields()  // También limpiamos los campos si falla
                onError(error) // Solo mostrar error, no navegar
            }
        )
    }
}


class CrearUsuarioViewModelFactory(
    private val repository: ICrearUsuarioRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrearUsuarioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CrearUsuarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
