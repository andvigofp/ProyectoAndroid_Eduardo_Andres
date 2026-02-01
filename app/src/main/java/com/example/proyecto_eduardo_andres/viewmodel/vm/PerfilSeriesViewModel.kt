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
            onError = { error -> Log.e("PerfilSeriesViewModel", "Error al actualizar", error) },
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
