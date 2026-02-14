package com.example.proyecto_eduardo_andres.data.repository.seriesRepository

import android.content.Context
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.room.dao.SerieDao
import com.example.proyecto_eduardo_andres.data.room.entity.Serie
import com.example.proyecto_eduardo_andres.modelo.VideoClubOnlineSeriesData
import com.example.proyecto_eduardo_andres.remote.api.SerieApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeriesRepositoryHibrido(
    private val context: Context,
    private val api: SerieApiService,
    private val serieDao: SerieDao
) : ISeriesRepository {

    override fun obtenerSeries(
        onError: (Throwable) -> Unit,
        onSuccess: (List<VideoClubOnlineSeriesData>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            var series: List<VideoClubOnlineSeriesData>? = null

            try {
                // ==========================
                // Intento REMOTO
                // ==========================
                val response = api.obtenerSeries()

                if (response.isSuccessful && response.body() != null) {

                    val body = response.body()!!

                    // Convertimos DTO → Room Entity
                    val entidades = body.map { dto ->
                        Serie(
                            id = dto.id,
                            nombre = dto.nombre,
                            categoria = dto.categoria,
                            imagen = dto.imagen,
                            descripcion = dto.descripcion
                        )
                    }

                    // Guardamos en Room
                    serieDao.deleteAll()
                    serieDao.insertAll(entidades)

                    // Convertimos a modelo UI
                    series = entidades.map {
                        VideoClubOnlineSeriesData(
                            id = it.id,
                            nombre = resolveStringResource(it.nombre),
                            categoria = resolveStringResource(it.categoria),
                            imagen = resolveDrawableResource(it.imagen),
                            descripcion = resolveStringResource(it.descripcion)
                        )
                    }
                }

            } catch (e: Exception) {
                // Si falla retrofit, seguimos con Room
            }

            // ==========================
            // LOCAL (Room)
            // ==========================
            if (series == null) {

                val local = serieDao.getAll()

                series = local.map {
                    VideoClubOnlineSeriesData(
                        id = it.id,
                        nombre = resolveStringResource(it.nombre),
                        categoria = resolveStringResource(it.categoria),
                        imagen = resolveDrawableResource(it.imagen),
                        descripcion = resolveStringResource(it.descripcion)
                    )
                }
            }

            // ==========================
            // Resultado final
            // ==========================
            withContext(Dispatchers.Main) {
                if (series.isNullOrEmpty())
                    onError(Throwable("No hay series disponibles"))
                else
                    onSuccess(series!!)
            }
        }
    }

    // =============================
    // Helpers igual que tu Retrofit
    // =============================

    private fun resolveStringResource(value: String): Int {
        if (value.isBlank()) return R.string.app_name
        val name = value.substringAfter("R.string.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "string", context.packageName)
        return if (resId != 0) resId else R.string.app_name
    }

    private fun resolveDrawableResource(value: String): Int? {
        if (value.isBlank()) return null
        val name = value.substringAfter("R.drawable.").substringAfterLast('.')
        val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
        return if (resId != 0) resId else null
    }
}
