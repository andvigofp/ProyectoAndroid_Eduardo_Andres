package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.IRecuperarPasswordRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.RecuperarPasswordUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class RecuperarPasswordViewModel(
    private val repository: IRecuperarPasswordRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(RecuperarPasswordUiState())
    val uiState: StateFlow<RecuperarPasswordUiState> = _uiState.asStateFlow()

    // Variables para el diálogo (mismo patrón que LoginViewModel)
    var loginMessage by mutableStateOf("")
        private set

    var showLoginDialog by mutableStateOf(false)
        private set

    var dialogTitle by mutableStateOf("Recuperar contraseña")
        private set

    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        _uiState.update { it.copy(repeatPassword = newRepeatPassword) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun recuperarPassword(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val state = _uiState.value
        if (state.password != state.repeatPassword) {
            showErrorDialog("Error", "Las contraseñas no coinciden")
            onError("Las contraseñas no coinciden")
            return
        }

        // Llamada al repositorio
        repository.recuperarPassword(
            email = state.email,
            newPassword = state.password,
            onError = { error ->
                val msg = error.message ?: "Error al recuperar contraseña"
                showErrorDialog("Error", msg)
                onError(msg)
            },
            onSuccess = {
                showSuccessDialog("Contraseña actualizada", "La contraseña se ha actualizado correctamente")
                // Ejecutar callback de éxito después de un breve retraso
                viewModelScope.launch {
                    kotlinx.coroutines.delay(1200)
                    onSuccess()
                }
            }
        )
    }

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

    fun dismissDialog() {
        showLoginDialog = false
        loginMessage = ""
    }
}

class RecuperarPasswordViewModelFactory(
    private val repository: IRecuperarPasswordRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecuperarPasswordViewModel::class.java)) {
            return RecuperarPasswordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}