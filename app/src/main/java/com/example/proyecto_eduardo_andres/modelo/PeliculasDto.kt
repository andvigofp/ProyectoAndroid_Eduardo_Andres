package com.example.proyecto_eduardo_andres.modelo

import com.example.proyecto_eduardo_andres.R

data class PeliculasDto(
    val peliculas: List<VideoClubOnlinePeliculasData> = listOf(
        // --- DRAMA ---
        pelicula(
            id = "peli_001",
            categoria = R.string.drama,
            imagen = R.drawable.ic_cadena_perpetua,
            nombre = R.string.cadena_perpetua,
            descripcion = R.string.banquero_inocente
        ),
        pelicula(
            id = "peli_002",
            categoria = R.string.drama,
            imagen = R.drawable.ic_big_fish,
            nombre = R.string.big_fish,
            descripcion = R.string.un_hijo_padre
        ),
        pelicula(
            id = "peli_003",
            categoria = R.string.drama,
            imagen = R.drawable.ic_padrino,
            nombre = R.string.el_padrino,
            descripcion = R.string.familia_mafiosa
        ),
        pelicula(
            id = "peli_004",
            categoria = R.string.drama,
            imagen = R.drawable.ic_lista_scindler,
            nombre = R.string.la_lista_schindler,
            descripcion = R.string.salva_cientos_judios
        ),
        pelicula(
            id = "peli_005",
            categoria = R.string.drama,
            imagen = R.drawable.ic_nunca_jamas,
            nombre = R.string.descubriendo_nunca_jamas,
            descripcion = R.string.peter_pan
        ),
        pelicula(
            id = "peli_006",
            categoria = R.string.drama,
            imagen = R.drawable.ic_vida_bella,
            nombre = R.string.la_vida_es_bella,
            descripcion = R.string.padre_protege_hijo
        ),

        // --- ACCIÓN ---
        pelicula(
            id = "peli_007",
            categoria = R.string.accion,
            imagen = R.drawable.ic_mad_max,
            nombre = R.string.mad_max,
            descripcion = R.string.futuro_postapocaliptico
        ),
        pelicula(
            id = "peli_008",
            categoria = R.string.accion,
            imagen = R.drawable.ic_jhon_wick,
            nombre = R.string.john_wick,
            descripcion = R.string.exassino_busca_venganza
        ),
        pelicula(
            id = "peli_009",
            categoria = R.string.accion,
            imagen = R.drawable.ic_mision_imposible,
            nombre = R.string.mision_imposible,
            descripcion = R.string.organizacion_criminal
        ),
        pelicula(
            id = "peli_010",
            categoria = R.string.accion,
            imagen = R.drawable.ic_gladiator,
            nombre = R.string.gladiator,
            descripcion = R.string.gladiador_busca_venganza
        ),
        pelicula(
            id = "peli_011",
            categoria = R.string.accion,
            imagen = R.drawable.ic_terminator_dos,
            nombre = R.string.terminator_dos,
            descripcion = R.string.cybor_protege_futuro
        ),
        pelicula(
            id = "peli_012",
            categoria = R.string.accion,
            imagen = R.drawable.ic_die_hard,
            nombre = R.string.die_hard,
            descripcion = R.string.policia_lucha_terroristas
        ),

        // --- TERROR ---
        pelicula(
            id = "peli_013",
            categoria = R.string.terror,
            imagen = R.drawable.ic_el_conjuro,
            nombre = R.string.el_conjuro,
            descripcion = R.string.pareja_investiga_paranormales
        ),
        pelicula(
            id = "peli_014",
            categoria = R.string.terror,
            imagen = R.drawable.ic_it,
            nombre = R.string.it,
            descripcion = R.string.niños_enfrenta_payaso
        ),
        pelicula(
            id = "peli_015",
            categoria = R.string.terror,
            imagen = R.drawable.ic_siniestro,
            nombre = R.string.siniestro,
            descripcion = R.string.escritor_encuentra_videos_perturbados
        ),
        pelicula(
            id = "peli_016",
            categoria = R.string.terror,
            imagen = R.drawable.ic_la_monja,
            nombre = R.string.la_monja,
            descripcion = R.string.monja_ivestiga_aparicion_demoniaca
        ),
        pelicula(
            id = "peli_017",
            categoria = R.string.terror,
            imagen = R.drawable.ic_viernes_trece,
            nombre = R.string.viernes_trece,
            descripcion = R.string.asesino_acecha_jovenes_campamento
        ),
        pelicula(
            id = "peli_018",
            categoria = R.string.terror,
            imagen = R.drawable.ic_pesadialla_calle_elm,
            nombre = R.string.pesadilla_en_elm_street,
            descripcion = R.string.asesino_ataca_en_sueños
        ),

        // --- DIBUJOS ---
        pelicula(
            id = "peli_019",
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_toy_story,
            nombre = R.string.toy_story,
            descripcion = R.string.jueguetes_cobran_vida_humanos_no_ven
        ),
        pelicula(
            id = "peli_020",
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_up,
            nombre = R.string.up,
            descripcion = R.string.anciano_viaja_casa_globos
        ),
        pelicula(
            id = "peli_021",
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_shreck,
            nombre = R.string.shrek,
            descripcion = R.string.ogro_rescata_princesa
        ),
        pelicula(
            id = "peli_022",
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_buscando_nemo,
            nombre = R.string.buscando_a_nemo,
            descripcion = R.string.pez_busca_hijo_oceano
        ),
        pelicula(
            id = "peli_023",
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_coco,
            nombre = R.string.coco,
            descripcion = R.string.niño_viaja_mundo_muertos
        ),
        pelicula(
            id = "peli_024",
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_frozen,
            nombre = R.string.frozen,
            descripcion = R.string.dos_hermas_poder_amor_magia
        )
    )
)