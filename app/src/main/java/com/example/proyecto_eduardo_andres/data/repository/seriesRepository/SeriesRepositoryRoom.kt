package com.example.proyecto_eduardo_andres.data.repository.seriesRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.data.room.dao.SerieDao
import com.example.proyecto_eduardo_andres.data.room.entity.Serie
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.remote.api.SerieApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Implementación del repositorio de series utilizando arquitectura híbrida:
 * - Primero intenta obtener los datos desde la API remota (Retrofit).
 * - Si la llamada es exitosa, guarda los datos en Room.
 * - Si falla la red, utiliza los datos almacenados localmente en Room.
 *
 * @param context Contexto necesario para convertir las entidades Room en modelos
 *                de UI mediante la función toUiModel(context).
 * @param api Servicio Retrofit para obtener las series desde el servidor.
 * @param serieDao DAO de Room para persistir y consultar series localmente.
 *
 * @author Andrés
 */
class SeriesRepositoryRoom(
    private val context: Context,
    private val api: SerieApiService,
    private val serieDao: SerieDao
) : ISeriesRepository {

    /**
     * Obtiene el catálogo de series.
     *
     * Flujo:
     * 1. Intenta obtener datos remotos.
     * 2. Si éxito → guarda en Room y devuelve resultado.
     * 3. Si falla → usa datos locales.
     *
     * @param onError Callback que se ejecuta si no hay datos disponibles.
     * @param onSuccess Callback que devuelve la lista de series para mostrar en UI.
     */
    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = api.obtenerSeries()

                if (response.isSuccessful && response.body() != null) {

                    val entidades = response.body()!!.map { dto ->
                        Serie(
                            id = dto.id,
                            nombre = dto.nombre,
                            categoria = dto.categoria,
                            imagen = dto.imagen,
                            descripcion = dto.descripcion
                        )
                    }

                    serieDao.deleteAll()
                    serieDao.insertAll(entidades)

                    val resultado = entidades.map { it.toUiModel(context) }

                    withContext(Dispatchers.Main) {
                        onSuccess(resultado)
                    }

                    return@launch
                }

            } catch (_: Exception) {
                // Si falla red → seguimos offline
            }

            // Fallback local (Room)
            val local = serieDao.getAll()

            withContext(Dispatchers.Main) {
                if (local.isEmpty())
                    onError(Throwable("No hay series disponibles"))
                else
                    onSuccess(local.map { it.toUiModel(context) })
            }
        }
    }
}
