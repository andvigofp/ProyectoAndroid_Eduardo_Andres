package com.example.proyecto_eduardo_andres.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.naveHost.SessionEvents
import com.example.proyecto_eduardo_andres.repository.UserRepositoryInMemory
import com.example.proyecto_eduardo_andres.ui.page.VideoClubSearchPeliculasScreen
import com.example.proyecto_eduardo_andres.viewData.logingData.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    private val _userRepo = UserRepositoryInMemory()

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

    fun logging() {

        _userRepo.login(_uiState.value.email, _uiState.value.password, onError = {}) { userDTO ->
            //llamar al evento login nav
            viewModelScope.launch {
                SessionEvents.emitNavigation(VideoClubSearchPeliculasScreen(userDTO.id))
            }
        }
    }
}
