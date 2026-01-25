package com.example.proyecto_eduardo_andres.viewData.listaPeliculasData

import com.example.proyecto_eduardo_andres.R

data class PeliculasData(
    val peliculas: List<VideoClubOnlinePeliculasData> = listOf(
        // --- DRAMA ---
        pelicula(
            id = 1,
            categoria = R.string.drama,
            imagen = R.drawable.ic_cadena_perpetua,
            nombre = R.string.cadena_perpetua,
            descripcion = R.string.banquero_inocente
        ),
        pelicula(
            id = 2,
            categoria =R.string.drama,
            imagen= R.drawable.ic_big_fish,
            nombre = R.string.big_fish,
            descripcion = R.string.un_hijo_padre
        ),
        pelicula(
            id = 3,
            categoria = R.string.drama,
            imagen = R.drawable.ic_padrino,
            nombre = R.string.el_padrino,
            descripcion = R.string.familia_mafiosa
        ),
        pelicula(
            id = 4,
            categoria = R.string.drama,
            imagen = R.drawable.ic_lista_scindler,
            nombre = R.string.la_lista_schindler,
            descripcion = R.string.salva_cientos_judios
        ),
        pelicula(
            id = 5,
            categoria = R.string.drama,
            imagen = R.drawable.ic_nunca_jamas,
            nombre = R.string.descubriendo_nunca_jamas,
            descripcion = R.string.peter_pan
        ),
        pelicula(
            id = 6,
            categoria = R.string.drama,
            imagen = R.drawable.ic_vida_bella,
            nombre = R.string.la_vida_es_bella,
            descripcion = R.string.padre_protege_hijo
        ),


        // --- ACCIÓN ---
        pelicula(
            id = 7,
            categoria = R.string.accion,
            imagen = R.drawable.ic_mad_max,
            nombre = R.string.mad_max,
            descripcion = R.string.futuro_postapocaliptico
        ),
        pelicula(
            id = 8,
            categoria = R.string.accion,
            imagen = R.drawable.ic_jhon_wick,
            nombre = R.string.john_wick,
            descripcion = R.string.exassino_busca_venganza
        ),
        pelicula(
            id = 9,
            categoria = R.string.accion,
            imagen = R.drawable.ic_mision_imposible,
            nombre = R.string.mision_imposible,
            descripcion = R.string.organizacion_criminal
        ),
        pelicula(
            id = 10,
            categoria = R.string.accion,
            imagen = R.drawable.ic_gladiator,
            nombre = R.string.gladiator,
            descripcion = R.string.gladiador_busca_venganza
        ),
        pelicula(
            id = 11,
            categoria = R.string.accion,
            imagen = R.drawable.ic_terminator_dos,
            nombre = R.string.terminator_dos,
            descripcion = R.string.cybor_protege_futuro
        ),
        pelicula(
            id = 12,
            categoria = R.string.accion,
            imagen = R.drawable.ic_die_hard,
            nombre = R.string.die_hard,
            descripcion = R.string.policia_lucha_terroristas
        ),

        // --- TERROR ---
        pelicula(
            id = 13,
            categoria = R.string.terror,
            imagen = R.drawable.ic_el_conjuro,
            nombre = R.string.el_conjuro,
            descripcion = R.string.pareja_investiga_paranormales
        ),
        pelicula(
            id = 14,
            categoria = R.string.terror,
            imagen = R.drawable.ic_it,
            nombre = R.string.it,
            descripcion = R.string.niños_enfrenta_payaso
        ),
        pelicula(
            id = 15,
            categoria = R.string.terror,
            imagen = R.drawable.ic_siniestro,
            nombre = R.string.siniestro,
            descripcion = R.string.escritor_encuentra_videos_perturbados
        ),
        pelicula(
            id = 16,
            categoria = R.string.terror,
            imagen = R.drawable.ic_la_monja,
            nombre = R.string.la_monja,
            descripcion = R.string.monja_ivestiga_aparcion_demoniaca
        ),
        pelicula(
            id = 17,
            categoria = R.string.terror,
            imagen = R.drawable.ic_viernes_trece,
            nombre = R.string.viernes_trece,
            descripcion = R.string.asesino_acecha_jovenes_campamento
        ),
        pelicula(
            id = 18,
            categoria = R.string.terror,
            imagen = R.drawable.ic_pesadialla_calle_elm,
            nombre = R.string.pesadilla_en_elm_street,
            descripcion = R.string.asesino_ataca_en_sueños
        ),

        // --- DIBUJOS ---
        pelicula(
            id = 19,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_toy_story,
            nombre = R.string.toy_story,
            descripcion = R.string.jueguetes_cobran_vida_humanos_no_ven
        ),
        pelicula(
            id = 20,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_up,
            nombre = R.string.up,
            descripcion = R.string.anciano_viaja_casa_globos
        ),
        pelicula(
            id = 21,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_shreck,
            nombre = R.string.shrek,
            descripcion = R.string.ogro_rescata_princesa
        ),
        pelicula(
            id = 22,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_buscando_nemo,
            nombre = R.string.buscando_a_nemo,
            descripcion = R.string.pez_busca_hijo_oceano
        ),
        pelicula(
            id = 23,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_coco,
            nombre = R.string.coco,
            descripcion = R.string.niño_viaja_mundo_muertos
        ),
        pelicula(
            id = 24,
            categoria = R.string.dibujos,
            imagen = R.drawable.ic_frozen,
            nombre = R.string.frozen,
            descripcion = R.string.dos_hermas_poder_amor_magia
        )
    )
)