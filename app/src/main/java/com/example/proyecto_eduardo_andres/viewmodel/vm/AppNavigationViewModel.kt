package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.IUserRepository
import com.example.proyecto_eduardo_andres.naveHost.RouteNavigation
import com.example.proyecto_eduardo_andres.viewmodel.ustate.AppNavigationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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

        // Para este ejemplo, asumimos que guardas un usuario en SharedPreferences
        val sharedPref = getApplication<Application>()
            .getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        val userId = sharedPref.getInt("user_id", -1)

        if (userId != -1) {
            // Intentamos recuperar info del usuario
            userRepository.getUser(
                id = userId,
                onError = {
                    _uiState.value = AppNavigationUiState(
                        isCheckingSession = false,
                        currentUser = null,
                        initialRoute = RouteNavigation.Login
                    )
                },
                onSuccess = { user ->
                    _uiState.value = AppNavigationUiState(
                        isCheckingSession = false,
                        currentUser = user,
                        initialRoute = RouteNavigation.VideoClubPeliculas(user.id.toString())
                    )
                }
            )
        } else {
            _uiState.value = AppNavigationUiState(
                isCheckingSession = false,
                currentUser = null,
                initialRoute = RouteNavigation.Login
            )
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
