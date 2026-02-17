package com.example.proyecto_eduardo_andres.data.room.dao

import androidx.room.*
import com.example.proyecto_eduardo_andres.data.room.entity.User

/**
 *
 * DAO (Data Access Object) para la gestión de usuarios
 * en la base de datos Room.
 *
 * Esta interfaz define las operaciones relacionadas con:
 * - Consulta de usuarios
 * - Autenticación (login)
 * - Inserción individual y múltiple
 * - Gestión de sesión persistente (keep_logged)
 * - Eliminación de datos
 *
 * Se utiliza dentro de una arquitectura moderna Android
 *  * (Room + Repository + ViewModel + Jetpack Compose),
 *  * garantizando persistencia eficiente y acceso asíncrono
 *  * mediante corrutinas.
 *
 * @author Andrés
 */
@Dao
interface UserDao {

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     *
     * La función es suspend para ejecutarse dentro de una corrutina
     * y evitar bloquear el hilo principal (Main Thread).
     *
     * @return Lista completa de entidades User.
     */
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    /**
     * Obtiene un usuario concreto a partir de su identificador.
     *
     * @param id Identificador único del usuario.
     *
     * @return La entidad User correspondiente o null si no existe.
     */
    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getById(id: String): User?

    /**
     * Verifica las credenciales de un usuario para iniciar sesión.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña asociada a la cuenta.
     *
     * @return El usuario autenticado si las credenciales son correctas,
     * o null si no coinciden.
     *
     * Se limita el resultado a un único registro mediante LIMIT 1.
     */
    @Query("SELECT * FROM user WHERE email = :email AND passwd = :password LIMIT 1")
    suspend fun login(email: String, password: String): User?

    /**
     * Inserta un usuario en la base de datos.
     *
     * @param user Entidad User que se desea guardar.
     *
     * Si ya existe un usuario con el mismo ID,
     * se reemplaza automáticamente gracias a
     * OnConflictStrategy.REPLACE.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    /**
     * Inserta múltiples usuarios en la base de datos.
     *
     * @param users Lista de entidades User a insertar.
     *
     * En caso de conflicto de clave primaria,
     * los registros existentes se reemplazan automáticamente.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    /**
     * Actualiza el estado de sesión persistente de un usuario.
     *
     * @param id Identificador único del usuario.
     * @param keepLogged Valor booleano que indica si el usuario
     * debe mantenerse con la sesión iniciada (true) o no (false).
     *
     * Se utiliza normalmente para implementar la opción
     * "Recordar sesión" en aplicaciones Android.
     */
    @Query("UPDATE user SET keep_logged = :keepLogged WHERE id = :id")
    suspend fun updateKeepLogged(id: String, keepLogged: Boolean)

    /**
     * Elimina todos los usuarios almacenados en la base de datos.
     *
     */
    @Query("DELETE FROM user")
    suspend fun deleteAll()

    /**
     * Obtiene el usuario que tiene la sesión activa
     * (keep_logged = true).
     *
     * @return El usuario que permanece logueado o null
     * si no existe ninguno.
     *
     * Este método no es suspend porque puede utilizarse
     * en contextos donde se necesite acceso inmediato,
     * aunque se recomienda ejecutarlo fuera del hilo principal
     * si la base de datos es grande.
     */
    @Query("SELECT * FROM user WHERE keep_logged = 1 LIMIT 1")
    fun getLoggedUser(): User?

}