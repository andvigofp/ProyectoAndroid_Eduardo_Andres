package com.example.proyecto_eduardo_andres.viewData.listaPeliculasData

import com.example.proyecto_eduardo_andres.R

data class PeliculasData(
    val peliculas: List<VideoClubOnlinePeliculasData> = listOf(
        // --- DRAMA ---
        pelicula(R.string.drama, R.drawable.ic_cadena_perpetua, R.string.cadena_perpetua),
        pelicula(R.string.drama, R.drawable.ic_big_fish, R.string.big_fish),
        pelicula(R.string.drama, R.drawable.ic_padrino, R.string.el_padrino),
        pelicula(R.string.drama, R.drawable.ic_lista_scindler, R.string.la_lista_schindler),
        pelicula(R.string.drama, R.drawable.ic_nunca_jamas, R.string.descubriendo_nunca_jamas),
        pelicula(R.string.drama, R.drawable.ic_vida_bella, R.string.la_vida_es_bella),
        pelicula(R.string.drama, R.drawable.ic_terminator_dos, R.string.die_hard), // Temporal

        // --- ACCIÓN ---
        pelicula(R.string.accion, R.drawable.ic_mad_max, R.string.mad_max),
        pelicula(R.string.accion, R.drawable.ic_jhon_wick, R.string.john_wick),
        pelicula(R.string.accion, R.drawable.ic_mision_imposible, R.string.mision_imposible),
        pelicula(R.string.accion, R.drawable.ic_gladiator, R.string.gladiator),
        pelicula(R.string.accion, R.drawable.ic_terminator_dos, R.string.terminator_dos),

        // --- TERROR ---
        pelicula(R.string.terror, R.drawable.ic_el_conjuro, R.string.el_conjuro),
        pelicula(R.string.terror, R.drawable.ic_it, R.string.it),
        pelicula(R.string.terror, R.drawable.ic_siniestro, R.string.siniestro),
        pelicula(R.string.terror, R.drawable.ic_la_monja, R.string.la_monja),
        pelicula(R.string.terror, R.drawable.ic_viernes_trece, R.string.viernes_trece),
        pelicula(R.string.terror, R.drawable.ic_pesadialla_calle_elm, R.string.pesadilla_en_elm_street),

        // --- DIBUJOS ---
        pelicula(R.string.dibujos, R.drawable.ic_toy_story, R.string.toy_story),
        pelicula(R.string.dibujos, R.drawable.ic_up, R.string.up),
        pelicula(R.string.dibujos, R.drawable.ic_toy_story, R.string.shrek), // Temporal
        pelicula(R.string.dibujos, R.drawable.ic_buscando_nemo, R.string.buscando_a_nemo),
        pelicula(R.string.dibujos, R.drawable.ic_coco, R.string.coco),
        pelicula(R.string.dibujos, R.drawable.ic_coco, R.string.frozen) // Temporal
    )
)


