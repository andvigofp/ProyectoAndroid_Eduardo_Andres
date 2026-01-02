package com.example.proyecto_eduardo_andres.viewData.alquilerDevolverSeriesData

import com.example.proyecto_eduardo_andres.R

data class SeriesAlquilerDevolverData(
    val nombreSeries: List<VideoClubOnlineAlquilarSeriesUiState> = listOf(
        // DRAMA
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_mad_men,
            nombreSerie = R.string.mad_men,
            descripcion = R.string.vida_don_draper
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_the_wire,
            nombreSerie = R.string.the_wire,
            descripcion = R.string.un_retrato_crudo_realista
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_better_call_saul,
            nombreSerie = R.string.better_call_saul,
            descripcion = R.string.precuela_de_breaking_bad
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_this_is_us,
            nombreSerie = R.string.this_is_us,
            descripcion = R.string.una_conmovedora_historia
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.the_good_wife,
            nombreSerie = R.string.the_good_wife,
            descripcion = R.string.tras_un_escandalo_politico
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_euphoria,
            nombreSerie = R.string.euphoria,
            descripcion = R.string.un_grupo_adodeslencete_explora_identidad_amor
        ),

        // ACCIÓN
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_24_horas,
            nombreSerie = R.string.veinticuatro_horas,
            descripcion = R.string.jack_bauder_detener_terroristas
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_prision_break,
            nombreSerie = R.string.prison_break,
            descripcion = R.string.un_homre_idea_plan_sacar_hermano_corredor_muerte
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_narcos,
            nombreSerie = R.string.narcos,
            descripcion = R.string.basada_hecos_reales_ascenso_caida_pablo_escobar
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_vikings_vahalla,
            nombreSerie = R.string.vikings_valhalla,
            descripcion = R.string.secuela_vikings
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_sons_of_anarchy,
            nombreSerie = R.string.sons_of_anarchy,
            descripcion = R.string.la_vida_club_motociclista
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_stricke_back,
            nombreSerie = R.string.strike_back,
            descripcion = R.string.dos_agentes_elite_enfrentan_peligrosas_misiones
        ),

        // TERROR
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_the_haunting_of_hill_house,
            nombreSerie = R.string.the_haunting_of_hill_house,
            descripcion = R.string.una_familia_se_enfrentra_traumas
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_american_horror_story,
            nombreSerie = R.string.american_horror_story,
            descripcion = R.string.cada_temporada_presenta_una_historia_terror
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_american_horror_story, // Temporal: ic_the_walking_dead está corrupto
            nombreSerie = R.string.the_walking_dead,
            descripcion = R.string.un_grupo_supervientes_lucha_zombis
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_penny_dreadful,
            nombreSerie = R.string.penny_dreadful,
            descripcion = R.string.reune_personajes_clasicos_terror
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_mariane,
            nombreSerie = R.string.marianne,
            descripcion = R.string.una_escritoria_de_terror_descubre_sus_pesadillas
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_bates_motel,
            nombreSerie = R.string.bates_motel,
            descripcion = R.string.explora_la_joventud_de_norman_bates
        ),

        // DIBUJOS
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_los_simpson,
            nombreSerie = R.string.los_simpson,
            descripcion = R.string.familia_ciudad_springfield
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_bod_esponja,
            nombreSerie = R.string.bob_esponja,
            descripcion = R.string.aventuras_fondo_mar_en_fondo_bikini
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_rick_morty,
            nombreSerie = R.string.rick_y_morty,
            descripcion = R.string.cientifico_y_nieto_viajan_por_universo_tienen_aventuras
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_avatar_la_leyenda_de_aang,
            nombreSerie = R.string.avatar_la_leyenda_de_aang,
            descripcion = R.string.joven_avatar_debe_dominar_cuatro_elementos
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_hora_aventuras,
            nombreSerie = R.string.hora_de_aventuras,
            descripcion = R.string.finn_perro_magico_viven_aventuras
        ),
        VideoClubOnlineAlquilarSeriesUiState(
            imagen = R.drawable.ic_gravity_falls,
            nombreSerie = R.string.gravity_falls,
            descripcion = R.string.dos_hermanos_descubren_los_misterios_sobrenaturales
        )
    )
)