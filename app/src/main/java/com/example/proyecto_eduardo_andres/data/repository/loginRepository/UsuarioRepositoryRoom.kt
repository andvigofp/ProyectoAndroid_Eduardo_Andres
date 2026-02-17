package com.example.proyecto_eduardo_andres.data.repository.loginRepository

import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.UsuarioApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Repositorio híbrido encargado del proceso de autenticación.
 *
 * Esta clase combina:
 * - Retrofit → consulta remota de usuarios.
 * - Room → persistencia local y login offline.
 *
 * Flujo de login:
 * 1. Intenta autenticar contra API remota.
 * 2. Si encuentra usuario válido → lo guarda en Room.
 * 3. Si falla red → intenta login local con Room.
 *
 * @property usuarioApi Servicio Retrofit para obtener usuarios remotos.
 * @property userDao DAO de Room para acceso a base de datos local.
 *
 * @author Andrés
 * @see UsuarioApiService
 * @see UserDao
 */
class UserRepositoryHibridoLogin(
    private val usuarioApi: UsuarioApiService,
    private val userDao: UserDao
) {

    /**
     * Realiza login híbrido (remoto + local).
     *
     * Primero intenta autenticación remota.
     * Si falla red o no encuentra usuario válido,
     * intenta autenticación local en Room.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña introducida.
     * @param keepLogged Indica si el usuario desea mantener sesión activa.
     *
     * @return [UserDTO] si las credenciales son válidas,
     * o null si no existe el usuario.
     */
    suspend fun login(
        email: String,
        password: String,
        keepLogged: Boolean
    ): UserDTO? {

        var userFound: UserDTO? = null

        // INTENTO REMOTO (RETROFIT)
        try {
            val response = usuarioApi.obtenerUsuarios()

            if (response.isSuccessful && response.body() != null) {

                val remoteUser = response.body()!!
                    .firstOrNull { it.email == email && it.passwd == password }

                remoteUser?.let { dto ->

                    val roomUser = dto.toUser().copy(keepLogged = keepLogged)
                    userDao.insert(roomUser)

                    userFound = UserDTO(
                        dto.id,
                        dto.name,
                        dto.email,
                        dto.passwd,
                        keepLogged
                    )
                }
            }

        } catch (_: Exception) {
            // Si falla red → seguimos offline
        }

        // INTENTO LOCAL (ROOM)
        if (userFound == null) {

            val localUser = userDao.login(email, password)

            localUser?.let {

                val updatedUser = it.copy(keepLogged = keepLogged)
                userDao.insert(updatedUser)

                userFound = UserDTO(
                    updatedUser.id,
                    updatedUser.name,
                    updatedUser.email,
                    updatedUser.passwd,
                    keepLogged
                )
            }
        }

        return userFound
    }

    /**
     * Obtiene un usuario específico desde Room por su ID.
     *
     * @param id Identificador único del usuario.
     *
     * @return [UserDTO] si el usuario existe en base de datos,
     * o null si no se encuentra.
     */
    suspend fun getUser(id: String): UserDTO? {
        val user = userDao.getById(id)
        return user?.let {
            UserDTO(it.id, it.name, it.email, it.passwd, it.keepLogged)
        }
    }
}