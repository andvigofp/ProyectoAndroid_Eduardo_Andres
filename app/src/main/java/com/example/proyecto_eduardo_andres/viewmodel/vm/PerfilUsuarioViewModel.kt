package com.example.proyecto_eduardo_andres.viewmodel.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.IPerfilUsuarioRepository
import com.example.proyecto_eduardo_andres.viewmodel.ustate.PerfilUsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
private val alquilerRepository: IAlquilerPeliculasRepository? = null
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUsuarioUiState())
    val uiState: StateFlow<PerfilUsuarioUiState> = _uiState.asStateFlow()

    // Cargar usuario por email
    fun cargarUsuario(id: String) {
        repository.getUsuarioPorId(
            id = id,
            onError = { error ->
                Log.e("PerfilViewModel", "Error al cargar usuario", error)
            },
            onSuccess = { usuario ->
                _uiState.value = PerfilUsuarioUiState(
                    nombreUsuario = usuario.name,
                    email = usuario.email,
                    password = usuario.password,
                    isEditing = false,
                    userId = usuario.id.toString()
                )
                cargarPeliculasAlquiladas(usuario.id.toString())
            }
        )
    }

    // Cargar películas alquiladas usando UUID como String
    private fun cargarPeliculasAlquiladas(userId: String) {
        alquilerRepository?.obtenerPeliculasAlquiladas(
            userId = userId, // actualizar repositorio para aceptar String
            onError = { error -> Log.e("PerfilViewModel", "Error al cargar películas", error) },
            onSuccess = { peliculas ->
                _uiState.update { it.copy(peliculasAlquiladas = peliculas) }
            }
        )
    }

    fun recargarPeliculasAlquiladas(userId: String) {
        cargarPeliculasAlquiladas(userId.toString())
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

    // PerfilUsuarioViewModel.kt
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

        fun cerrarConfirmacionDialog() {
            _uiState.update { it.copy(showConfirmacionDialog = false) }
        }
    }

// Factory actualizado para usar email en lugar de userId
class PerfilUsuarioViewModelFactory(
    private val userId: String,  // <--- ahora es id
    private val repository: IPerfilUsuarioRepository,
    private val alquilerRepository: IAlquilerPeliculasRepository? = null
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilUsuarioViewModel::class.java)) {
            val viewModel = PerfilUsuarioViewModel(repository, alquilerRepository)
            viewModel.cargarUsuario(userId) // <--- pasamos id
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}