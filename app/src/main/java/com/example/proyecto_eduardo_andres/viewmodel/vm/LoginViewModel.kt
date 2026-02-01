package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.viewmodel.ustate.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val userRepository: com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryInMemory
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    var loginMessage by mutableStateOf("")
        private set

    var showLoginDialog by mutableStateOf(false)
        private set

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
        userRepository.login(
            email = _uiState.value.email,
            password = _uiState.value.password,
            onError = { throwable ->
                loginMessage = "Login fallido: ${throwable.message}"
                showLoginDialog = true
            },
            onSuccess = { user ->
                loginMessage = "¡Bienvenido ${user.name}!"
                showLoginDialog = true
                onSuccess()
            }
        )
    }

    fun dismissDialog() {
        showLoginDialog = false
    }
}

class LoginViewModelFactory(
    private val repository: com.example.proyecto_eduardo_andres.data.repository.loginRepository.UserRepositoryInMemory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
