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

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id Identificador único del usuario.
     * @param onError Callback que se ejecuta si ocurre un error durante la obtención.
     * @param onSuccess Callback que devuelve el [UserDTO] del usuario encontrado.
     */
    fun getUser(id: String, onError: (Throwable) -> Unit, onSuccess: (UserDTO) -> Unit)

    /**
     * Realiza el proceso de autenticación del usuario.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @param keepLogged Indica si el usuario desea mantener la sesión iniciada.
     * @param onError Callback que se ejecuta si las credenciales son incorrectas
     * o ocurre un error.
     * @param onSuccess Callback que devuelve el [UserDTO] autenticado correctamente.
     */
    fun login(
        email: String,
        password: String,
        keepLogged: Boolean,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    )

    /**
     * Guarda la configuración del usuario autenticado en almacenamiento local
     * (por ejemplo DataStore o SharedPreferences).
     *
     * @param user Configuración del usuario representada mediante [UserRepo.UserConfig].
     * @param onSuccess Callback que devuelve la configuración guardada.
     * @param onError Callback que se ejecuta si falla el guardado.
     */
    fun loginUser(
        user: UserRepo.UserConfig,
        onSuccess: (UserRepo.UserConfig) -> Unit,
        onError: () -> Unit
    )

    /**
     * Cierra la sesión del usuario actual eliminando su persistencia local.
     *
     * @param onSuccess Callback que se ejecuta cuando el logout se realiza correctamente.
     * @param onError Callback que se ejecuta si ocurre un error durante el proceso.
     */
    fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit)

    /**
     * Devuelve el usuario actualmente autenticado, si existe.
     *
     * @return Objeto [UserRepo.UserConfig] con la información del usuario
     * o null si no hay sesión activa.
     */
    fun getCurrentUser(): UserRepo.UserConfig?
}
