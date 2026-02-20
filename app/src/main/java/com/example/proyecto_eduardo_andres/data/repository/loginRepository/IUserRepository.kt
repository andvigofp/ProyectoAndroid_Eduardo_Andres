package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import com.example.proyecto_eduardo_andres.modelo.UserDTO

/**
 * Contrato del repositorio encargado de la gestión de usuarios.
 *
 * Define las operaciones principales relacionadas con:
 * - Obtención de usuario
 * - Inicio de sesión
 * - Persistencia de sesión
 * - Cierre de sesión
 * - Recuperación del usuario actual
 *
 * Esta interfaz permite desacoplar la capa de datos
 * (Room, Retrofit, DataStore, etc.) de la lógica de negocio.
 *
 * @author Eduardo
 * @see UserDTO
 * @see UserRepo
 */
interface IUserRepository {

    fun login(
        email: String,
        password: String,
        keepLogged: Boolean,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    )

    fun getUser(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    )

    fun loginUser(
        user: UserRepo.UserConfig,
        onSuccess: (UserRepo.UserConfig) -> Unit,
        onError: () -> Unit
    )

    fun loggoutUser(
        onSuccess: () -> Unit,
        onError: () -> Unit
    )

    suspend fun isServerAvailable(): Boolean

    fun getCurrentUser(): UserRepo.UserConfig?
}