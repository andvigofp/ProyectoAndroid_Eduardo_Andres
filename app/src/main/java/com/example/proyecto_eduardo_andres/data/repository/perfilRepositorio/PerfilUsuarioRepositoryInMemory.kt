package com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Implementación que obtiene los datos del usuario desde una API remota
 * pero no persiste cambios. Ideal para testing o entornos simples.
 *
 * @author Eduardo
 * @see Implementación en memoria del repositorio de perfil de usuario
 * @param apiService Servicio Retrofit encargado de obtener los usuarios desde la API.
 */
class PerfilUsuarioRepositoryInMemory(
    private val apiService: UsuarioApiService
) : IPerfilUsuarioRepository {

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id Identificador único del usuario.
     * @param onError Callback que se ejecuta si ocurre un error durante la petición.
     * @param onSuccess Callback que devuelve el usuario encontrado como UserDTO.
     */
    override fun getUsuarioPorId(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.obtenerUsuarios()
                if (response.isSuccessful) {
                    val usuarios = response.body() ?: emptyList()
                    val usuarioDto = usuarios.find { it.id == id } // <-- buscar por id

                    if (usuarioDto != null) {
                        val usuario = UserDTO(
                            id = usuarioDto.id,
                            name = usuarioDto.name,
                            email = usuarioDto.email,
                            password = usuarioDto.passwd,
                            keepLogged = usuarioDto.keepLogged
                        )
                        withContext(Dispatchers.Main) { onSuccess(usuario) }
                    } else {
                        withContext(Dispatchers.Main) { onError(Throwable("Usuario no encontrado")) }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Error al obtener usuarios: ${response.code()}"))
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    /**
     * Actualiza la información del usuario.
     *
     * Nota: En esta implementación en memoria no está soportada
     * la actualización remota del usuario.
     *
     * @param usuario Objeto UserDTO con los datos actualizados del usuario.
     * @param onError Callback que se ejecuta indicando que la operación no está implementada.
     * @param onSuccess Callback que se ejecutaría si la actualización fuese exitosa.
     */
    override fun actualizarUsuario(
        usuario: UserDTO,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        onError(Throwable("Actualización de usuario no implementada en la API"))
    }
}