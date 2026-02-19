package com.example.proyecto_eduardo_andres.data.repository.crearUsuario

import com.example.proyecto_eduardo_andres.modelo.UserDTO

/**
 * Repositorio encargado de la creación de nuevos usuarios en el sistema.
 *
 * Define el contrato que deben implementar las distintas fuentes de datos
 * (Retrofit, Room o InMemory) para registrar un usuario.
 *
 * @author Andrés
 * @see CrearUsuarioScreen
 * @see UserDTO
 */
interface ICrearUsuarioRepository {

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param nombre Nombre completo del usuario que se desea registrar.
     * @param email Correo electrónico del usuario.
     * @param password Contraseña asociada al nuevo usuario.
     * @param onError Callback que se ejecuta si ocurre un error durante el proceso
     * de creación (por ejemplo, error de red o validación).
     * @param onSuccess Callback que se ejecuta cuando el usuario ha sido creado
     * correctamente. Devuelve un objeto [UserDTO] con los datos del usuario registrado.
     */
    fun crearUsuario(
        nombre: String,
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    )
}