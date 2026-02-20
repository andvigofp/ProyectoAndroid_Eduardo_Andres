package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository
import com.example.proyecto_eduardo_andres.data.repository.loginRepository.IUserRepository
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.IPerfilUsuarioRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.PerfilUsuarioUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * @author Andrés
 * @see PerfilUsuarioViewModelFactory
 *
 * Esta clase:
 * - Gestiona el estado de la pantalla de Perfil de Usuario (Películas).
 * - Carga los datos del usuario a partir de su ID (UUID).
 * - Carga las películas alquiladas asociadas al usuario.
 * - Permite editar y actualizar la información del usuario.
 * - Controla la visualización de diálogos informativos y de confirmación.
 *
 * Sigue la arquitectura MVVM:
 * - ViewModel → Contiene la lógica de presentación.
 * - Repository → Gestiona operaciones de usuario.
 * - AlquilerRepository → Gestiona películas alquiladas.
 *
 * Utiliza:
 * - MutableStateFlow para gestionar el estado interno.
 * - StateFlow para exponer estado inmutable a la UI.
 * - update {} para mantener inmutabilidad.
 *
 *
 * @param repository Repositorio encargado de operaciones del usuario.
 * @param alquilerRepository Repositorio encargado de obtener
 * las películas alquiladas (opcional).
 *
 * @see PerfilUsuarioUiState
 * @see IPerfilUsuarioRepository
 * @see IAlquilerPeliculasRepository
 * @see UserDTO
 * @see ViewModel
 * @see MutableStateFlow
 * @see StateFlow
 */
class PerfilUsuarioViewModel(
    private val repository: IPerfilUsuarioRepository,
    private val alquilerRepository: IAlquilerPeliculasRepository? = null,
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUsuarioUiState())
    val uiState: StateFlow<PerfilUsuarioUiState> = _uiState.asStateFlow()

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

                    // Si el servidor cae mientras edita → salir de edición
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
                Log.e("PerfilViewModel", "Error al cargar usuario", error)

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

                cargarPeliculasAlquiladas(usuario.id)
            }
        )
    }

    // -------------------------------------------------
    // CARGAR PELÍCULAS
    // -------------------------------------------------

    private fun cargarPeliculasAlquiladas(userId: String) {

        alquilerRepository?.obtenerPeliculasAlquiladas(
            userId = userId,
            onError = { error ->
                Log.e("PerfilViewModel", "Error al cargar películas", error)
            },
            onSuccess = { peliculas ->
                _uiState.update {
                    it.copy(peliculasAlquiladas = peliculas)
                }
            }
        )
    }

    fun recargarPeliculasAlquiladas(userId: String) {
        cargarPeliculasAlquiladas(userId)
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
            mostrarInfoDialog(
                "Modo Offline",
                "No se puede modificar el perfil sin conexión."
            )
            return
        }

        _uiState.update { it.copy(isEditing = !it.isEditing) }
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

// Factory actualizado para usar email en lugar de userId
class PerfilUsuarioViewModelFactory(
    private val userId: String,  // <--- ahora es id
    private val repository: IPerfilUsuarioRepository,
    private val alquilerRepository: IAlquilerPeliculasRepository? = null,
    private val userRepository: IUserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilUsuarioViewModel::class.java)) {
            val viewModel = PerfilUsuarioViewModel(repository, alquilerRepository, userRepository)
            viewModel.cargarUsuario(userId) // <--- pasamos id
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}