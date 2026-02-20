package com.example.proyecto_eduardo_andres.viewmodel.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.IUserRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.LoginUiState
import com.example.proyecto_eduardo_andres.vista.componente.componenteLogin.LoginMode
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * @author Andrés
 * @see LoginViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de Login.
 * - Controla los campos de email, contraseña y opción "Mantener sesión".
 * - Ejecuta la autenticación delegando la lógica al repositorio.
 * - Gestiona estados de carga (loading).
 * - Controla diálogos de éxito y error.
 * - Expone el ID del usuario autenticado.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación y validación.
 * - Repository → Encargado de la autenticación y persistencia.
 *
 * Utiliza:
 * - StateFlow para exponer el estado reactivo del formulario.
 * - MutableStateFlow para modificar el estado interno.
 * - mutableStateOf para estados simples de UI (diálogos).
 * - viewModelScope para ejecutar corrutinas seguras.
 * - Dispatchers.Main para actualizar la UI tras operaciones async.
 *
 *
 * @param userRepository Repositorio encargado de autenticar
 * y recuperar información del usuario.
 *
 * @see LoginUiState
 * @see IUserRepository
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 * @see mutableStateOf
 * @see viewModelScope
 */
class LoginViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    var loggedInUserId: String? by mutableStateOf(null)
        private set

    var loginMessage by mutableStateOf("")
        private set

    var showLoginDialog by mutableStateOf(false)
        private set

    var dialogTitle by mutableStateOf("Login")
        private set

    private var serverMonitorJob: Job? = null


    // MONITOR AUTOMÁTICO DEL SERVIDOR
    fun startServerMonitoring() {

        serverMonitorJob?.cancel()

        serverMonitorJob = viewModelScope.launch {

            while (isActive) {

                val available = userRepository.isServerAvailable()

                val newMode =
                    if (available) LoginMode.RETROFIT
                    else LoginMode.ROOM

                if (_uiState.value.loginMode != newMode) {
                    _uiState.update {
                        it.copy(loginMode = newMode)
                    }
                }

                delay(3000) // revisa cada 3 segundos
            }
        }
    }

    fun stopServerMonitoring() {
        serverMonitorJob?.cancel()
    }


    // CAMBIOS DE CAMPOS
    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun onKeepLoggedChange(keepLogged: Boolean) {
        _uiState.update { it.copy(keepLogged = keepLogged) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }


    // BOTÓN ACCEDER
    fun onLoginClicked() {

        viewModelScope.launch {

            val available = userRepository.isServerAvailable()

            if (!available) {
                _uiState.update { it.copy(loginMode = LoginMode.ROOM) }
                loginAsGuest()
                return@launch
            }

            _uiState.update { it.copy(loginMode = LoginMode.RETROFIT) }

            val email = _uiState.value.email.trim()
            val password = _uiState.value.password

            if (email.isEmpty()) {
                showDialog("Error", "El email no puede estar vacío")
                return@launch
            }

            if (password.isEmpty()) {
                showDialog("Error", "La contraseña no puede estar vacía")
                return@launch
            }

            loginOnline(email, password)
        }
    }


    // LOGIN ONLINE
    private fun loginOnline(email: String, password: String) {

        _uiState.update { it.copy(isLoading = true) }

        userRepository.login(
            email = email,
            password = password,
            keepLogged = _uiState.value.keepLogged,

            onError = { throwable ->

                val isServerDown =
                    throwable is IOException ||
                            throwable.cause is IOException

                if (isServerDown) {

                    _uiState.update {
                        it.copy(
                            loginMode = LoginMode.ROOM,
                            isLoading = false
                        )
                    }

                    loginAsGuest()

                } else {

                    _uiState.update { it.copy(isLoading = false) }

                    showDialog(
                        "Login fallido",
                        throwable.message ?: "Error desconocido"
                    )
                }
            },

            onSuccess = { user ->

                loggedInUserId = user.id

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoginSuccessful = true
                    )
                }

                showDialog(
                    "¡Bienvenido!",
                    "Hola ${user.name}"
                )
            }
        )
    }

    // -------------------------------------------------
    // LOGIN INVITADO
    // -------------------------------------------------

    private fun loginAsGuest() {

        loggedInUserId = "GUEST"

        _uiState.update {
            it.copy(
                isLoginSuccessful = true,
                isLoading = false
            )
        }

        showDialog(
            "Modo Invitado",
            "Has iniciado sesión sin conexión"
        )
    }


    // DIÁLOGOS
    private fun showDialog(title: String, message: String) {
        dialogTitle = title
        loginMessage = message
        showLoginDialog = true
    }

    fun dismissDialog() {
        showLoginDialog = false
        loginMessage = ""
    }

    fun resetState() {
        _uiState.value = LoginUiState()
        loggedInUserId = null
        loginMessage = ""
        showLoginDialog = false
    }

    override fun onCleared() {
        super.onCleared()
        serverMonitorJob?.cancel()
    }
}

class LoginViewModelFactory(
    private val userRepository: IUserRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}