package com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio

import com.example.proyecto_eduardo_andres.data.room.dao.UserDao
import com.example.proyecto_eduardo_andres.data.room.entity.User
import com.example.proyecto_eduardo_andres.modelo.UserDTO
import com.example.proyecto_eduardo_andres.remote.api.PerfilUsuarioApiService
import com.example.proyecto_eduardo_andres.remote.dto.UsuarioDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PerfilUsuarioRepositoryRoom(
    private val api: PerfilUsuarioApiService,
    private val userDao: UserDao
) : IPerfilUsuarioRepository {

    override fun getUsuarioPorId(
        id: String,
        onError: (Throwable) -> Unit,
        onSuccess: (UserDTO) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                // Intento remoto
                val response = api.obtenerPerfilUsuario()

                if (response.isSuccessful && response.body() != null) {

                    val users = response.body()!!
                    val found = users.firstOrNull { it.id == id }

                    if (found != null) {

                        val entity = User(
                            id = found.id,
                            name = found.name,
                            email = found.email,
                            passwd = found.passwd,
                            keepLogged = found.keepLogged
                        )

                        // Guardamos en Room
                        userDao.insert(entity)

                        val dto = UserDTO(
                            id = found.id,
                            name = found.name,
                            email = found.email,
                            password = found.passwd,
                            keepLogged = found.keepLogged
                        )

                        withContext(Dispatchers.Main) { onSuccess(dto) }
                        return@launch
                    }
                }

            } catch (_: Exception) {
                // Si falla red → seguimos offline
            }

            // Room
            val local = userDao.getById(id)

            if (local != null) {

                val dto = UserDTO(
                    id = local.id,
                    name = local.name,
                    email = local.email,
                    password = local.passwd,
                    keepLogged = local.keepLogged
                )

                withContext(Dispatchers.Main) { onSuccess(dto) }

            } else {
                withContext(Dispatchers.Main) {
                    onError(Throwable("Usuario no encontrado"))
                }
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
                // Intento remoto
                val updated = UsuarioDto(
                    id = usuario.id.toString(),
                    name = usuario.name,
                    email = usuario.email,
                    passwd = usuario.password,
                    keepLogged = usuario.keepLogged
                )

                val response = api.actualizarUsuario(usuario.id.toString(), updated)

                if (response.isSuccessful) {

                    // Actualizamos Room
                    userDao.insert(
                        User(
                            id = usuario.id.toString(),
                            name = usuario.name,
                            email = usuario.email,
                            passwd = usuario.password,
                            keepLogged = usuario.keepLogged
                        )
                    )

                    withContext(Dispatchers.Main) { onSuccess() }
                    return@launch
                }

            } catch (_: Exception) {
                // Si falla red → seguimos offline
            }

            // Offline update
            userDao.insert(
                User(
                    id = usuario.id.toString(),
                    name = usuario.name,
                    email = usuario.email,
                    passwd = usuario.password,
                    keepLogged = usuario.keepLogged
                )
            )

            withContext(Dispatchers.Main) { onSuccess() }
        }
    }
}
