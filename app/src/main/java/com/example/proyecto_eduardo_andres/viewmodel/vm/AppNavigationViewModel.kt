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

/**
 * @author Andrés
 * @see AppNavigationViewModel
 *
 * Esta clase:
 * - Verifica si existe una sesión activa al iniciar la app.
 * - Define la ruta inicial de navegación.
 * - Mantiene el estado global de navegación.
 * - Permite cerrar sesión limpiando datos persistentes.
 *
 * Extiende AndroidViewModel porque necesita acceso
 * al contexto de aplicación (Application) para
 * trabajar con SharedPreferences.
 *
 * Utiliza:
 * - StateFlow para exponer estado reactivo.
 * - viewModelScope para corrutinas seguras.
 *
 * @param application Contexto global de la aplicación.
 * @param userRepository Repositorio encargado de obtener
 * información del usuario actual desde base de datos.
 *
 * @see AppNavigationUiState
 * @see IUserRepository
 * @see RouteNavigation
 * @see AndroidViewModel
 * @see ViewModel
 * @see Application
 * @see Context
 * @see MutableStateFlow
 * @see StateFlow
 * @see asStateFlow
 * @see viewModelScope
 */
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
                            id = currentUser.id,
                            name = currentUser.name,
                            email = currentUser.email,
                            password = "",
                            keepLogged = currentUser.keepLogged
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

        userRepository.loggoutUser(
            onSuccess = {
                _uiState.value = AppNavigationUiState(
                    isCheckingSession = false,
                    currentUser = null,
                    initialRoute = RouteNavigation.Login
                )
            },
            onError = {
                _uiState.value = AppNavigationUiState(
                    isCheckingSession = false,
                    currentUser = null,
                    initialRoute = RouteNavigation.Login
                )
            }
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
            return AppNavigationViewModel(
                application,
                userRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}