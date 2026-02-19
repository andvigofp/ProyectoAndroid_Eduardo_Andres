package com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository

import android.util.Log
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Esta clase gestiona la recuperación de contraseña utilizando
 * una API remota mediante Retrofit.
 *
 * Flujo:
 * 1. Obtiene la lista de usuarios desde la API.
 * 2. Busca el usuario por email.
 * 3. Actualiza su contraseña mediante una llamada POST.
 *
 * @author Andrés
 * @see Implementación Retrofit para recuperación de contraseña
 */
class RecuperarPasswordRepositoryRetrofit : IRecuperarPasswordRepository {
    private val api = RetrofitClient.recuperarPasswordApiExterna
    private val TAG = "RecuperarPasswordRepo"

    /**
     * Recupera la contraseña de un usuario realizando una actualización remota.
     *
     * @param email Email del usuario que desea recuperar la contraseña.
     * @param newPassword Nueva contraseña que se asignará al usuario.
     * @param onError Callback que se ejecuta si ocurre un error en la operación
     * (error de red, usuario no encontrado o error HTTP).
     * @param onSuccess Callback que se ejecuta cuando la contraseña se actualiza correctamente.
     */
    override fun recuperarPassword(
        email: String,
        newPassword: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d(TAG, "Iniciando recuperarPassword para email=$email")
                val response = api.obtenerUsuarios()

                if (!response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Error al obtener usuarios: HTTP ${response.code()} ${response.message()}"))
                    }
                    return@launch
                }

                val users = response.body() ?: emptyList()
                val found = users.firstOrNull { it.email.equals(email, ignoreCase = true) }

                if (found == null) {
                    withContext(Dispatchers.Main) { onError(Throwable("No existe ningún usuario con ese email")) }
                    return@launch
                }

                val updated = UsuarioDto(
                    id = found.id,
                    name = found.name,
                    email = found.email,
                    passwd = newPassword,
                    keepLogged = false
                )

                val updateResponse = try {
                    api.actualizarUsuario(found.id, updated)
                } catch (e: Exception) {
                    Log.e(TAG, "Error en llamada actualizarUsuario(id): ${e.message}", e)
                    withContext(Dispatchers.Main) { onError(e) }
                    return@launch
                }

                if (updateResponse.isSuccessful) {
                    withContext(Dispatchers.Main) { onSuccess() }
                } else {
                    withContext(Dispatchers.Main) {
                        onError(Throwable("Error al actualizar usuario: HTTP ${updateResponse.code()} ${updateResponse.message()}"))
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception en recuperarPassword: ${e.message}", e)
                withContext(Dispatchers.Main) { onError(e) }
            }
        }
    }
}