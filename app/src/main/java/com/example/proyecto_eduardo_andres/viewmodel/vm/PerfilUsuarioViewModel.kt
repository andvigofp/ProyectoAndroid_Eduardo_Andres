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

class PerfilUsuarioViewModel(
    private val repository: com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.IPerfilUsuarioRepository,
    private val alquilerRepository: com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository? = null
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUsuarioUiState())
    val uiState: StateFlow<PerfilUsuarioUiState> = _uiState.asStateFlow()

    // Cargar usuario
    fun cargarUsuario(id: String) {
        repository.getUsuario(
            id = id,
            onError = { error ->
                Log.e("PerfilViewModel", "Error al cargar usuario", error)
            },
            onSuccess = { usuario ->
                _uiState.value = PerfilUsuarioUiState(
                    nombreUsuario = usuario.name,
                    email = usuario.email,
                    password = usuario.password, // ya no es nullable
                    isEditing = false,
                    userId = usuario.id ?: id
                )
                cargarPeliculasAlquiladas(usuario.id ?: id)
            }
        )
    }

    // Cargar películas alquiladas
    private fun cargarPeliculasAlquiladas(userId: String) {
        val userIdInt = userId.toIntOrNull() ?: return
        alquilerRepository?.obtenerPeliculasAlquiladas(
            userId = userIdInt,
            onError = { /* manejar error */ },
            onSuccess = { peliculas ->
                _uiState.update { it.copy(peliculasAlquiladas = peliculas) }
            }
        )
    }

    fun recargarPeliculasAlquiladas(userId: String) {
        cargarPeliculasAlquiladas(userId)
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

    // Guardar cambios
    fun guardarCambios() {
        val state = _uiState.value
        if (!state.isModificarButtonEnabled) return

        val usuario = UserDTO(
            id = state.userId,
            name = state.nombreUsuario,
            email = state.email,
            password = state.password
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

class PerfilUsuarioViewModelFactory(
    private val userId: String,
    private val repository: com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.IPerfilUsuarioRepository,
    private val alquilerRepository: com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository? = null
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilUsuarioViewModel::class.java)) {
            val viewModel = PerfilUsuarioViewModel(repository, alquilerRepository)
            viewModel.cargarUsuario(userId)
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
