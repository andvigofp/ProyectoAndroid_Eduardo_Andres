package com.example.proyecto_eduardo_andres.modelo

import com.example.proyecto_eduardo_andres.R

data class SeriesDto(
    val series: List<VideoClubOnlineSeriesData> = listOf(

        // ========= DRAMA =========
        serie(
            id = 1,
            categoria = R.string.drama,
            imagen = R.drawable.ic_mad_men,
            nombre = R.string.mad_men,
            descripcion = R.string.vida_don_draper
        ),
        serie(
            id = 2,
            categoria = R.string.drama,
            imagen = R.drawable.ic_the_wire,
            nombre = R.string.the_wire,
            descripcion = R.string.un_retrato_crudo_realista
        ),
        serie(
            id = 3,
            categoria = R.string.drama,
            imagen = R.drawable.ic_better_call_saul,
            nombre = R.string.better_call_saul,
            descripcion = R.string.precuela_de_breaking_bad
        ),
        serie(
            id = 4,
            categoria = R.string.drama,
            imagen = R.drawable.ic_this_is_us,
            nombre = R.string.this_is_us,
            descripcion = R.string.una_conmovedora_historia
        ),
        serie(
            id = 5,
            categoria = R.string.drama,
            imagen = R.drawable.the_good_wife,
            nombre = R.string.the_good_wife,
            descripcion = R.string.tras_un_escandalo_politico
        ),
        serie(
            id = 6,
            categoria = R.string.drama,
            imagen = R.drawable.ic_euphoria,
            nombre = R.string.euphoria,
            descripcion = R.string.un_grupo_adodeslencete_explora_identidad_amor
        ),

        // ========= ACCIÓN =========
        serie(
            id = 7,
            categoria = R.string.accion,
            imagen = R.drawable.ic_24_horas,
            nombre = R.string.veinticuatro_horas,
            descripcion = R.string.jack_bauder_detener_terroristas
        ),
        serie(
            id = 8,
            categoria = R.string.accion,
            imagen = R.drawable.ic_prision_break,
            nombre = R.string.prison_break,
            descripcion = R.string.un_homre_idea_plan_sacar_hermano_corredor_muerte
        ),
        serie(
            id = 9,
            categoria = R.string.accion,
            imagen = R.drawable.ic_narcos,
            nombre = R.string.narcos,
            descripcion = R.string.basada_hecos_reales_ascenso_caida_pablo_escobar
        ),
        serie(
            id = 10,
            categoria = R.string.accion,
            imagen = R.drawable.ic_vikings_vahalla,
            nombre = R.string.vikings_valhalla,
            descripcion = R.string.secuela_vikings
        ),
        serie(
            id = 11,
            categoria = R.string.accion,
            imagen = R.drawable.ic_sons_of_anarchy,
            nombre = R.string.sons_of_anarchy,
            descripcion = R.string.la_vida_club_motociclista
        ),
        serie(
            id = 12,
            categoria = R.string.accion,
            imagen = R.drawable.ic_stricke_back,
            nombre = R.string.strike_back,
            descripcion = R.string.dos_agentes_elite_enfrentan_peligrosas_misiones
        ),

        // ========= TERROR =========
        serie(
            id = 13,
            categoria = R.string.terror,
            imagen = R.drawable.ic_the_haunting_of_hill_house,
            nombre = R.string.the_haunting_of_hill_house,
            descripcion = R.string.una_familia_se_enfrentra_traumas
        ),
        serie(
            id = 14,
            categoria = R.string.terror,
            imagen = R.drawable.ic_american_horror_story,
            nombre = R.string.american_horror_story,
            descripcion = R.string.cada_temporada_presenta_una_historia_terror
        ),
        serie(
            id = 15,
            categoria = R.string.terror,
            imagen = R.drawable.ic_american_horror_story, // temporal
            nombre = R.string.the_walking_dead,
            descripcion = R.string.un_grupo_supervientes_lucha_zombis
        ),
        serie(
            id = 16,
            categoria = R.string.terror,
            imagen = R.drawable.ic_penny_dreadful,
            nombre = R.string.penny_dreadful,
            descripcion = R.string.reune_personajes_clasicos_terror
        ),
        serie(
            id = 17,
            categoria = R.string.terror,
            imagen = R.drawable.ic_mariane,
            nombre = R.string.marianne,
            descripcion = R.string.una_escritoria_de_terror_descubre_sus_pesadillas
        ),
        serie(
            id = 18,
            categoria = R.string.terror,
            imagen = R.drawable.ic_bates_motel,
            nombre = R.string.bates_motel,
            descripcion = R.string.explora_la_joventud_de_norman_bates
        ),

        // ========= DIBUJOS =========
        serie(
            id = 19,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_los_simpson,
            nombre = R.string.los_simpson,
            descripcion = R.string.familia_ciudad_springfield
        ),
        serie(
            id = 20,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_bod_esponja,
            nombre = R.string.bob_esponja,
            descripcion = R.string.aventuras_fondo_mar_en_fondo_bikini
        ),
        serie(
            id = 21,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_rick_morty,
            nombre = R.string.rick_y_morty,
            descripcion = R.string.cientifico_y_nieto_viajan_por_universo_tienen_aventuras
        ),
        serie(
            id = 22,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_avatar_la_leyenda_de_aang,
            nombre = R.string.avatar_la_leyenda_de_aang,
            descripcion = R.string.joven_avatar_debe_dominar_cuatro_elementos
        ),
        serie(
            id = 23,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_hora_aventuras,
            nombre = R.string.hora_de_aventuras,
            descripcion = R.string.finn_perro_magico_viven_aventuras
        ),
        serie(
            id = 24,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_gravity_falls,
            nombre = R.string.gravity_falls,
            descripcion = R.string.dos_hermanos_descubren_los_misterios_sobrenaturales
        )
    )
)