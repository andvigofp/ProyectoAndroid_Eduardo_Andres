package com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio

import com.example.proyecto_eduardo_andres.modelo.UserDTO

/**
 *
 * Interfaz que define las operaciones relacionadas con
 * la obtención y actualización de datos del usuario.
 *
 *  @author Eduardo
 *  @see Repositorio para la gestión del perfil de usuario
 */
interface IPerfilUsuarioRepository {

    /**
     * Obtiene un usuario a partir de su identificador único.
     *
     * @param id Identificador único del usuario.
     * @param onError Callback que se ejecuta si ocurre un error durante la obtención.
     * @param onSuccess Callback que devuelve el usuario encontrado como UserDTO.
     *
     */
    fun getUsuarioPorId(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    )

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuario Objeto UserDTO con los datos actualizados del usuario.
     * @param onError Callback que se ejecuta si ocurre un error durante la actualización.
     * @param onSuccess Callback que se ejecuta cuando la actualización se realiza correctamente.
     */
    fun actualizarUsuario(
        usuario: UserDTO,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    )
}