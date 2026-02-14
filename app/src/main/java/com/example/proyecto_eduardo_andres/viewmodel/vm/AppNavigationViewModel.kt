package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.app.Application
import android.content.Context
import androidx.compose.runtime.key
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.IUserRepository
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.viewmodel.ustate.AppNavigationUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppNavigationViewModel(
    application: Application,
    private val userRepository: IUserRepository
) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(AppNavigationUiState())
    val uiState: StateFlow<AppNavigationUiState> = _uiState.asStateFlow()

    init {
        checkUserSession()
    }

    fun checkUserSession() {
        _uiState.value = _uiState.value.copy(isCheckingSession = true)

        viewModelScope.launch(Dispatchers.IO) {

            val currentUser = userRepository.getCurrentUser()

            withContext(Dispatchers.Main) {
                if (currentUser != null && currentUser.keepLogged) {
                    _uiState.value = AppNavigationUiState(
                        isCheckingSession = false,
                        currentUser = UserDTO(
                            currentUser.id,
                            currentUser.name,
                            currentUser.email,
                            currentUser.password,
                            currentUser.keepLogged
                        ),
                        initialRoute = RouteNavigation.VideoClubPeliculas(currentUser.id)
                    )
                } else {
                    _uiState.value = AppNavigationUiState(
                        isCheckingSession = false,
                        currentUser = null,
                        initialRoute = RouteNavigation.Login
                    )
                }
            }
        }
    }



    fun logout() {
        // Limpiar SharedPreferences
        val sharedPref = getApplication<Application>()
            .getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()

        _uiState.value = AppNavigationUiState(
            isCheckingSession = false,
            currentUser = null,
            initialRoute = RouteNavigation.Login
        )
    }
}

class AppNavigationViewModelFactory(
    private val application: Application,
    private val userRepository: IUserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppNavigationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppNavigationViewModel(application, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
