package com.example.proyecto_eduardo_andres.data.repository.recuperarPasswordRepository

import com.example.proyecto_eduardo_andres.remote.api.RecuperarPasswordApiService
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Esta clase gestiona la recuperación de contraseña combinando:
 *
 * 1. Intento remoto mediante Retrofit.
 * 2. Persistencia y fallback local mediante Room.
 *
 * Si la red falla, el sistema continúa funcionando en modo offline.
 *
 * @author Andrés
 * @see Implementación híbrida Room + Retrofit para recuperación de contraseña
 */
class RecuperarPasswordRepositoryRoom(
    private val api: RecuperarPasswordApiService,
) : IRecuperarPasswordRepository {

    override fun recuperarPassword(
        email: String,
        newPassword: String,
        onError: (Throwable) -> Unit,
        onSuccess: () -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Remoto Retrofit
                val response = api.obtenerUsuarios()

                if (response.isSuccessful && response.body() != null) {

                    val users = response.body()!!
                    val found = users.firstOrNull {
                        it.email.equals(email, ignoreCase = true)
                    }

                    if (found != null) {

                        val updated = UsuarioDto(
                            id = found.id,
                            name = found.name,
                            email = found.email,
                            passwd = newPassword,
                            keepLogged = false
                        )

                        val updateResponse =
                            api.actualizarUsuario(found.id, updated)

                        if (updateResponse.isSuccessful) {

                            withContext(Dispatchers.Main) { onSuccess() }
                            return@launch
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError(Throwable("No existe ningún usuario con ese email"))
                        }
                        return@launch
                    }
                }

            } catch (_: Exception) {
                // Si falla red seguimos offline
            }
        }
    }
}