package com.example.proyecto_eduardo_andres.viewData.listaSeriesData

import com.example.proyecto_eduardo_andres.R
data class SeriesData(
    val series: List<VideoClubOnlineSeriesData> = listOf(
        // DRAMA
        serie(R.string.drama, R.drawable.ic_mad_men, R.string.mad_men),
        serie(R.string.drama, R.drawable.ic_the_wire, R.string.the_wire),
        serie(R.string.drama, R.drawable.ic_better_call_saul, R.string.better_call_saul),
        serie(R.string.drama, R.drawable.ic_this_is_us, R.string.this_is_us),
        serie(R.string.drama, R.drawable.the_good_wife, R.string.the_good_wife),
        serie(R.string.drama, R.drawable.ic_euphoria, R.string.euphoria),

        // ACCIÓN
        serie(R.string.accion, R.drawable.ic_24_horas, R.string.veinticuatro_horas),
        serie(R.string.accion, R.drawable.ic_prision_break, R.string.prison_break),
        serie(R.string.accion, R.drawable.ic_narcos, R.string.narcos),
        serie(R.string.accion, R.drawable.ic_vikings_vahalla, R.string.vikings_valhalla),
        serie(R.string.accion, R.drawable.ic_sons_of_anarchy, R.string.sons_of_anarchy),
        serie(R.string.accion, R.drawable.ic_stricke_back, R.string.strike_back),

        // TERROR
        serie(R.string.terror, R.drawable.ic_the_haunting_of_hill_house, R.string.the_haunting_of_hill_house),
        serie(R.string.terror, R.drawable.ic_american_horror_story, R.string.american_horror_story),
        serie(R.string.terror, R.drawable.ic_american_horror_story, R.string.the_walking_dead), // temporal
        serie(R.string.terror, R.drawable.ic_penny_dreadful, R.string.penny_dreadful),
        serie(R.string.terror, R.drawable.ic_mariane, R.string.marianne),
        serie(R.string.terror, R.drawable.ic_bates_motel, R.string.bates_motel),

        // DIBUJOS
        serie(R.string.dibujos, R.drawable.ic_los_simpson, R.string.los_simpson),
        serie(R.string.dibujos, R.drawable.ic_bod_esponja, R.string.bob_esponja),
        serie(R.string.dibujos, R.drawable.ic_rick_morty, R.string.rick_y_morty),
        serie(R.string.dibujos, R.drawable.ic_avatar_la_leyenda_de_aang, R.string.avatar_la_leyenda_de_aang),
        serie(R.string.dibujos, R.drawable.ic_hora_aventuras, R.string.hora_de_aventuras),
        serie(R.string.dibujos, R.drawable.ic_gravity_falls, R.string.gravity_falls)
    )
)
