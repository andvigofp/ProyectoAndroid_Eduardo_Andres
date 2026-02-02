package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.viewmodel.ustate.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(
    private val userRepository: UserRepositoryInMemory
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Variables para el diálogo
    var loginMessage by mutableStateOf("")
        private set

    var showLoginDialog by mutableStateOf(false)
        private set

    var dialogTitle by mutableStateOf("Login")
        private set

    var loggedInUserId: String? by mutableStateOf(null)
        private set

    // Cambios en los campos
    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    // Función de login
    fun logging(onSuccess: () -> Unit = {}) {
        val email = _uiState.value.email.trim()
        val password = _uiState.value.password

        // Validación básica
        if (email.isEmpty() || password.isEmpty()) {
            showErrorDialog("Error", "Por favor, completa todos los campos")
            return
        }

        userRepository.login(
            email = email,
            password = password,
            onError = { throwable ->
                showErrorDialog("Login fallido", "Error: ${throwable.message ?: "Credenciales incorrectas"}")
            },
            onSuccess = { user ->
                showSuccessDialog("¡Bienvenido!", "¡Bienvenido ${user.name}!")
                loggedInUserId = user.id
                // Ejecutar callback de éxito después de un breve retraso
                viewModelScope.launch {
                    kotlinx.coroutines.delay(1500) // Esperar 1.5 segundos
                    onSuccess()
                }
            }
        )
    }

    // Funciones auxiliares para mostrar diálogos
    private fun showErrorDialog(title: String, message: String) {
        dialogTitle = title
        loginMessage = message
        showLoginDialog = true
    }

    private fun showSuccessDialog(title: String, message: String) {
        dialogTitle = title
        loginMessage = message
        showLoginDialog = true
    }

    // Cerrar diálogo
    fun dismissDialog() {
        showLoginDialog = false
        // Opcional: limpiar mensaje después de cerrar
        loginMessage = ""
    }

    // Resetear estado (para logout o limpiar)
    fun resetState() {
        _uiState.value = LoginUiState()
        loginMessage = ""
        showLoginDialog = false
        loggedInUserId = null
    }
}

class LoginViewModelFactory(
    private val repository: UserRepositoryInMemory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
