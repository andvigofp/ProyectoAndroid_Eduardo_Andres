package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.IRecuperarPasswordRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.RecuperarPasswordUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecuperarPasswordViewModel(
    private val repository: com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.IRecuperarPasswordRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(RecuperarPasswordUiState())
    val uiState: StateFlow<RecuperarPasswordUiState> = _uiState.asStateFlow()

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
            onError("Las contraseñas no coinciden")
            return
        }

        repository.recuperarPassword(
            email = state.email,
            newPassword = state.password,
            onError = { error ->
                onError(error.message ?: "Error al recuperar contraseña")
            },
            onSuccess = {
                onSuccess()
            }
        )
    }
}

class RecuperarPasswordViewModelFactory(
    private val repository: com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository.IRecuperarPasswordRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecuperarPasswordViewModel::class.java)) {
            return RecuperarPasswordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}