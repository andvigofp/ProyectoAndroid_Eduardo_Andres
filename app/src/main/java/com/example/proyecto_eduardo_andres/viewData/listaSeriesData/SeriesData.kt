package com.example.proyecto_eduardo_andres.viewData.listaSeriesData

import com.example.proyecto_eduardo_andres.R

data class SeriesData(
    val nombreSeries: List<VideoClubOnlineSeriesData> = listOf(
        //DRAMA
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_mad_men,
            nombreSerie = R.string.mad_men
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_the_wire,
            nombreSerie = R.string.the_wire
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_better_call_saul,
            nombreSerie = R.string.better_call_saul
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_this_is_us,
            nombreSerie = R.string.this_is_us
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.the_good_wife,
            nombreSerie = R.string.the_good_wife
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_euphoria,
            nombreSerie = R.string.euphoria
        ),

        //ACCION
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_24_horas,
            nombreSerie = R.string.veinticuatro_horas
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_prision_break,
            nombreSerie = R.string.prison_break
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_narcos,
            nombreSerie = R.string.narcos
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_vikings_vahalla,
            nombreSerie = R.string.vikings_valhalla
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_sons_of_anarchy,
            nombreSerie = R.string.sons_of_anarchy
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_stricke_back,
            nombreSerie = R.string.strike_back
        ),

        //TERROR
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_the_haunting_of_hill_house,
            nombreSerie = R.string.the_haunting_of_hill_house
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_american_horror_story,
            nombreSerie = R.string.american_horror_story
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_american_horror_story, // Temporal: ic_the_walking_dead está corrupto
            nombreSerie = R.string.the_walking_dead
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_penny_dreadful,
            nombreSerie = R.string.penny_dreadful
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_mariane,
            nombreSerie = R.string.marianne
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_bates_motel,
            nombreSerie = R.string.bates_motel
        ),

        //DIBUJOS
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_los_simpson,
            nombreSerie = R.string.los_simpson
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_bod_esponja,
            nombreSerie = R.string.bob_esponja
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_rick_morty,
            nombreSerie = R.string.rick_y_morty
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_avatar_la_leyenda_de_aang,
            nombreSerie = R.string.avatar_la_leyenda_de_aang
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_hora_aventuras,
            nombreSerie = R.string.hora_de_aventuras
        ),
        VideoClubOnlineSeriesData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_gravity_falls,
            nombreSerie = R.string.gravity_falls
        )
    )
)