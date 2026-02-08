package com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio

import android.content.Context
import android.util.Log
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.remote.api.PerfilUsuarioApiService
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PerfilUsuarioRepositoryRetrofit(
    private val api: PerfilUsuarioApiService = RetrofitClient.perfilUsuario
) : IPerfilUsuarioRepository {

    private val TAG = "PerfilUsuarioRepo"

    override fun getUsuarioPorId(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d(TAG, "Obteniendo usuario con id=$id")
                val response = api.obtenerPerfilUsuario() // GET a /json/user

                if (!response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Error al obtener usuarios: HTTP ${response.code()} ${response.message()}"))
                    }
                    return@launch
                }

                val users = response.body() ?: emptyList()
                val found = users.firstOrNull { it.id == id }

                if (found == null) {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Usuario no encontrado"))
                    }
                    return@launch
                }

                val usuario = UserDTO(
                    id = found.id,
                    name = found.name,
                    email = found.email,
                    password = found.passwd
                )

                withContext(Dispatchers.Main) { onSuccess(usuario) }

            } catch (e: Exception) {
                Log.e(TAG, "Exception al obtener usuario: ${e.message}", e)
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }

    override fun actualizarUsuario(
        usuario: UserDTO,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d(TAG, "Actualizando usuario con id=${usuario.id}")
                val updated = UsuarioDto(
                    id = usuario.id.toString(),
                    name = usuario.name,
                    email = usuario.email,
                    passwd = usuario.password
                )

                val updateResponse = api.actualizarUsuario(usuario.id.toString(), updated) // POST a /json/user/{id}

                if (updateResponse.isSuccessful) {
                    withContext(Dispatchers.Main) { onSuccess() }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Error al actualizar usuario: HTTP ${updateResponse.code()} ${updateResponse.message()}"))
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception al actualizar usuario: ${e.message}", e)
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }
}
