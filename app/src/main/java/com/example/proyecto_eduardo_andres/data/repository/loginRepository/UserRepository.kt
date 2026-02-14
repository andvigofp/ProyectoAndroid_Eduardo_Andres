package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.AuthApiService
import com.example.proyecto_eduardo_andres.remote.dto.LoginDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Implementacion en memoria de un repositorio de usuarios. Usar solo para ejemplos o pruebas.
 */
class UserRepositoryInMemory(
private val authApi: AuthApiService
) : IUserRepository {

    // Guardamos el usuario actual en memoria
    private var currentUser: UserRepo.UserConfig? = null
    // --------------------
    // getUser por ID (no implementado con memoria real, puedes simular)
    // --------------------

    override fun getUser(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit,
    ) {
        currentUser?.let {
            if (it.id == id) {
                onSuccess(UserDTO(it.id.toString(), it.name, it.email, "", it.keepLogged))
            } else {
                onError(Throwable("Usuario no encontrado en memoria"))
            }
        } ?: onError(Throwable("No hay usuario en memoria"))
    }



    // --------------------
    // login con API y guardamos en memoria
    // --------------------
    override fun login(
        email: String,
        password: String,
        keepLogged: Boolean,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = LoginDto(email = email, passwd = password)
                val response = authApi.login(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        // Creamos UserDTO y UserConfig en memoria
                        val user = UserDTO(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password = password,
                            keepLogged = keepLogged
                        )
                        currentUser = UserRepo.UserConfig(
                            id = body.id,
                            name = body.name,
                            email = body.email,
                            password= password,
                            keepLogged = keepLogged
                        )
                        withContext(Dispatchers.Main) { onSuccess(user) }
                    } else {
                        withContext(Dispatchers.Main) { onError(Throwable("Respuesta vacía")) }
                    }
                } else {
                    withContext(Dispatchers.Main) { onError(Throwable("Login fallido: ${response.code()}")) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    // --------------------
    // Guardar sesión en memoria
    // --------------------
    override fun loginUser(
        user: UserRepo.UserConfig,
        onSuccess: (UserRepo.UserConfig) -> Unit,
        onError: () -> Unit
    ) {
        currentUser = user
        onSuccess(user)
    }

    // --------------------
    // Cerrar sesión
    // --------------------
    override fun loggoutUser(onSuccess: () -> Unit, onError: () -> Unit) {
        currentUser = null
        onSuccess()
    }

    // --------------------
    // Obtener usuario actual
    // --------------------
    override fun getCurrentUser(): UserRepo.UserConfig? {
        return currentUser
    }
}
