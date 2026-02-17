package com.example.proyecto_eduardo_andres.data.repository.crearUsuario

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.dto.RegisterDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Implementación del repositorio de creación de usuario utilizando Retrofit.
 *
 * Esta clase realiza una llamada HTTP al servicio remoto para registrar
 * un nuevo usuario en el sistema.
 *
 * @property authApi Servicio Retrofit encargado de realizar la petición
 * de registro al backend.
 *
 * @author Andrés
 * @see ICrearUsuarioRepository
 * @see AuthApiService
 * @see RegisterDto
 * @see UserDTO
 */
class CrearUsuarioRepositoryInMemory(private val authApi: AuthApiService) :
   ICrearUsuarioRepository {

    /**
     * Crea un nuevo usuario enviando los datos al servidor mediante Retrofit.
     *
     * @param nombre Nombre completo del usuario que se desea registrar.
     * @param email Correo electrónico del usuario.
     * @param password Contraseña asociada al nuevo usuario.
     * @param onError Callback que se ejecuta si ocurre un error durante la
     * petición (error HTTP, excepción de red o respuesta vacía).
     * @param onSuccess Callback que se ejecuta cuando el usuario se registra
     * correctamente. Devuelve un objeto [UserDTO] con los datos del usuario creado.
     */
    override fun crearUsuario(
        nombre: String,
        email: String,
        password: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = RegisterDto(name = nombre, email = email, passwd = password)
                val response = authApi.register(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val user = UserDTO(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password = password,
                            keepLogged = false
                        )
                        withContext(Dispatchers.Main) {
                            onSuccess(user)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError(Throwable("Respuesta vacía"))
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Registro fallido: ${response.code()}"))
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }
}