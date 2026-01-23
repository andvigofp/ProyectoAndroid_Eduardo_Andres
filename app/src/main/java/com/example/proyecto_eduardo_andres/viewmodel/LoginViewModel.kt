package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.repository.loginRepository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.viewData.logingData.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _userRepo = UserRepositoryInMemory()

    // Usuario de ejemplo prellenado
    private val _uiState = MutableStateFlow(
        LoginUiState(
            email = "user1@example.com",
            password = "password1"
        )
    )

    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun logging(onSuccess: () -> Unit = {}) {
        _userRepo.login(
            email = _uiState.value.email,
            password = _uiState.value.password,
            onError = { throwable ->
                println("Login fallido: ${throwable.message}")
            },
            onSuccess = { userDTO ->
                println("Login exitoso: ${userDTO.name}")
                onSuccess() // Llamamos al callback del composable
            }
        )
    }
}
