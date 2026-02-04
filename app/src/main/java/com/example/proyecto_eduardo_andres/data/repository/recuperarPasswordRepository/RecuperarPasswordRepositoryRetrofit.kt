package com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository

import android.util.Log
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecuperarPasswordRepositoryRetrofit : IRecuperarPasswordRepository {
    private val api = RetrofitClient.recuperarPasswordApiExterna
    private val TAG = "RecuperarPasswordRepo"

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
                    passwd = newPassword
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
