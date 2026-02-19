package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.data.repository.alquilerSeriesRepository.IAlquilerSeriesRepository
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.IPerfilUsuarioRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.PerfilSeriesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
    private val alquilerRepository: IAlquilerSeriesRepository? = null
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilSeriesUiState())
    val uiState: StateFlow<PerfilSeriesUiState> = _uiState.asStateFlow()

    // Cargar usuario por ID (UUID)
    fun cargarUsuario(id: String) {
        repository.getUsuarioPorId(
            id = id,
            onError = { error ->
                Log.e("PerfilSeriesViewModel", "Error al cargar usuario", error)
            },
            onSuccess = { usuario ->
                _uiState.value = PerfilSeriesUiState(
                    nombreUsuario = usuario.name,
                    email = usuario.email,
                    password = usuario.password,
                    isEditing = false,
                    userId = usuario.id.toString() // UUID como String
                )
                // Ahora cargamos las series alquiladas
                cargarSeriesAlquiladas(usuario.id.toString())
            }
        )
    }

    // Cargar series alquiladas usando String UUID
    private fun cargarSeriesAlquiladas(userId: String) {
        alquilerRepository?.obtenerSeriesAlquiladas(
            userId = userId,
            onError = { error -> Log.e("PerfilSeriesViewModel", "Error al cargar series", error) },
            onSuccess = { series ->
                _uiState.update { it.copy(seriesAlquiladas = series) }
            }
        )
    }

    fun recargarSeriesAlquiladas(userId: String) {
        cargarSeriesAlquiladas(userId)
    }

    // Cambios de campos
    fun onNombreUsuarioChange(nombre: String) {
        _uiState.update { it.copy(nombreUsuario = nombre) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    // Edit mode
    fun toggleEditing() {
        _uiState.update { it.copy(isEditing = !it.isEditing) }
    }

    fun cerrarInfoDialog() {
        _uiState.update { it.copy(showInfoDialog = false) }
    }

    // Guardar cambios
    fun guardarCambios() {
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
            onError = { error -> Log.e("PerfilViewModel", "Error al actualizar", error) },
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

    fun cerrarConfirmacionDialog() {
        _uiState.update { it.copy(showConfirmacionDialog = false) }
    }
}


class PerfilSeriesViewModelFactory(
    private val userId: String, // UUID como String
    private val repository: IPerfilUsuarioRepository,
    private val alquilerRepository: IAlquilerSeriesRepository? = null
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilSeriesViewModel::class.java)) {
            val viewModel = PerfilSeriesViewModel(repository, alquilerRepository)
            viewModel.cargarUsuario(userId) // pasamos UUID directamente
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
