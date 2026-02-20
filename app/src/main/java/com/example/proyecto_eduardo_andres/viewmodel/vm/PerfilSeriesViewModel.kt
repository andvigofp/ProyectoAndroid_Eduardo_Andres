package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.IAlquilerSeriesRepository
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.IUserRepository
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.IPerfilUsuarioRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.PerfilSeriesUiState
import com.example.proyecto_eduardo_andres.vista.componente.componenteLogin.LoginMode
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * @author Eduardo
 * @see PerfilSeriesViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de Perfil de Usuario.
 * - Carga los datos del usuario a partir de su ID (UUID).
 * - Carga las series alquiladas asociadas al usuario.
 * - Permite editar y actualizar la información del usuario.
 * - Controla la visibilidad de diálogos informativos y de confirmación.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación.
 * - Repository → Gestiona el acceso a datos del usuario.
 * - AlquilerRepository → Gestiona las series alquiladas.
 *
 * Utiliza:
 * - MutableStateFlow para gestionar el estado interno.
 * - StateFlow para exponer estado inmutable a la UI.
 * - update {} para mantener inmutabilidad del estado.
 *
 *
 * @param repository Repositorio encargado de operaciones
 * relacionadas con el usuario.
 * @param alquilerRepository Repositorio encargado de
 * obtener las series alquiladas (opcional).
 *
 * @see PerfilSeriesUiState
 * @see IPerfilUsuarioRepository
 * @see IAlquilerSeriesRepository
 * @see UserDTO
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 */
class PerfilSeriesViewModel(
    private val repository: IPerfilUsuarioRepository,
    private val alquilerRepository: IAlquilerSeriesRepository? = null,
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilSeriesUiState())
    val uiState: StateFlow<PerfilSeriesUiState> = _uiState.asStateFlow()

    private var serverMonitorJob: Job? = null

    // -------------------------------------------------
    // MONITOR AUTOMÁTICO DEL SERVIDOR
    // -------------------------------------------------

    fun startServerMonitoring() {

        serverMonitorJob?.cancel()

        serverMonitorJob = viewModelScope.launch {

            while (isActive) {

                val available = userRepository.isServerAvailable()

                if (_uiState.value.isOnline != available) {

                    _uiState.update {
                        it.copy(isOnline = available)
                    }

                    // Si cae mientras edita → salir del modo edición
                    if (!available) {
                        _uiState.update {
                            it.copy(isEditing = false)
                        }
                    }
                }

                delay(3000)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        serverMonitorJob?.cancel()
    }

    // -------------------------------------------------
    // CARGAR USUARIO
    // -------------------------------------------------

    fun cargarUsuario(id: String) {

        repository.getUsuarioPorId(
            id = id,

            onError = { error ->
                Log.e("PerfilSeriesViewModel", "Error al cargar usuario", error)

                _uiState.update {
                    it.copy(
                        showInfoDialog = true,
                        infoDialogTitle = "Error",
                        infoDialogMessage = error.message ?: "No se pudo cargar el perfil"
                    )
                }
            },

            onSuccess = { usuario ->

                _uiState.update { state ->
                    state.copy(
                        nombreUsuario = usuario.name,
                        email = usuario.email,
                        password = usuario.password,
                        userId = usuario.id,
                        isEditing = false
                    )
                }

                cargarSeriesAlquiladas(usuario.id)
            }
        )
    }

    // -------------------------------------------------
    // CARGAR SERIES
    // -------------------------------------------------

    private fun cargarSeriesAlquiladas(userId: String) {

        alquilerRepository?.obtenerSeriesAlquiladas(
            userId = userId,
            onError = { error ->
                Log.e("PerfilSeriesViewModel", "Error al cargar series", error)
            },
            onSuccess = { series ->
                _uiState.update {
                    it.copy(seriesAlquiladas = series)
                }
            }
        )
    }

    fun recargarSeriesAlquiladas(userId: String) {
        cargarSeriesAlquiladas(userId)
    }

    // -------------------------------------------------
    // CAMBIOS DE CAMPOS
    // -------------------------------------------------

    fun onNombreUsuarioChange(nombre: String) {
        _uiState.update { it.copy(nombreUsuario = nombre) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    // -------------------------------------------------
    // MODO EDICIÓN
    // -------------------------------------------------

    fun toggleEditing() {

        if (!_uiState.value.isOnline) {

            _uiState.update {
                it.copy(
                    showInfoDialog = true,
                    infoDialogTitle = "Modo Invitado",
                    infoDialogMessage = "No puedes modificar el perfil en modo sin conexión."
                )
            }

            return
        }

        _uiState.update {
            it.copy(isEditing = !it.isEditing)
        }
    }

    // -------------------------------------------------
    // GUARDAR CAMBIOS
    // -------------------------------------------------

    fun guardarCambios() {

        if (!_uiState.value.isOnline) {
            mostrarInfoDialog(
                "Modo Offline",
                "No se puede guardar cambios sin conexión."
            )
            return
        }

        val state = _uiState.value

        if (!state.isModificarButtonEnabled) return

        val usuario = UserDTO(
            id = state.userId,
            name = state.nombreUsuario,
            email = state.email,
            password = state.password,
            keepLogged = false
        )

        repository.actualizarUsuario(
            usuario = usuario,

            onError = { error ->
                _uiState.update {
                    it.copy(
                        showInfoDialog = true,
                        infoDialogTitle = "Error",
                        infoDialogMessage = error.message ?: "Error desconocido"
                    )
                }
            },

            onSuccess = {
                _uiState.update {
                    it.copy(
                        isEditing = false,
                        showConfirmacionDialog = true
                    )
                }
            }
        )
    }

    // -------------------------------------------------
    // DIÁLOGOS
    // -------------------------------------------------

    fun mostrarInfoDialog(title: String, message: String) {
        _uiState.update {
            it.copy(
                showInfoDialog = true,
                infoDialogTitle = title,
                infoDialogMessage = message
            )
        }
    }

    fun cerrarInfoDialog() {
        _uiState.update { it.copy(showInfoDialog = false) }
    }

    fun cerrarConfirmacionDialog() {
        _uiState.update { it.copy(showConfirmacionDialog = false) }
    }
}

class PerfilSeriesViewModelFactory(
    private val userId: String, // UUID como String
    private val repository: IPerfilUsuarioRepository,
    private val alquilerRepository: IAlquilerSeriesRepository? = null,
    private val userRepository: IUserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilSeriesViewModel::class.java)) {
            val viewModel = PerfilSeriesViewModel(repository, alquilerRepository, userRepository)
            viewModel.cargarUsuario(userId) // pasamos UUID directamente
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
