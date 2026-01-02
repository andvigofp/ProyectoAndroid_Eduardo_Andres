package com.example.proyecto_eduardo_andres.viewData.listaPeliculasData

import com.example.proyecto_eduardo_andres.R

data class PeliculasData(
    val nombrePeliculas: List<VideoClubOnlinePeliculasData> = listOf(
        //DRAMA
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_cadena_perpetua,
            nombrePelicula = R.string.cadena_perpetua
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_big_fish,
            nombrePelicula = R.string.big_fish
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_padrino,
            nombrePelicula = R.string.el_padrino
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_lista_scindler,
            nombrePelicula = R.string.la_lista_schindler
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_nunca_jamas,
            nombrePelicula = R.string.descubriendo_nunca_jamas
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_vida_bella,
            nombrePelicula = R.string.la_vida_es_bella
        ),

        //ACCION
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_mad_max,
            nombrePelicula = R.string.mad_max
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_jhon_wick,
            nombrePelicula = R.string.john_wick
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_mision_imposible,
            nombrePelicula = R.string.mision_imposible
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_gladiator,
            nombrePelicula = R.string.gladiator
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.accion,
            imagen = R.drawable.ic_terminator_dos,
            nombrePelicula = R.string.terminator_dos
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.drama,
            imagen = R.drawable.ic_terminator_dos, // Temporal: ic_die_hard está corrupto
            nombrePelicula = R.string.die_hard
        ),

        //TERROR
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_el_conjuro,
            nombrePelicula = R.string.el_conjuro
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_it,
            nombrePelicula = R.string.it
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_siniestro,
            nombrePelicula = R.string.siniestro
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_la_monja,
            nombrePelicula = R.string.la_monja
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_viernes_trece,
            nombrePelicula = R.string.viernes_trece
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.terror,
            imagen = R.drawable.ic_pesadialla_calle_elm,
            nombrePelicula = R.string.pesadilla_en_elm_street
        ),

        //DIBUJOS
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_toy_story,
            nombrePelicula = R.string.toy_story
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_up,
            nombrePelicula = R.string.up
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_toy_story, // Temporal: ic_shreck está corrupto
            nombrePelicula = R.string.shrek
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_buscando_nemo,
            nombrePelicula = R.string.buscando_a_nemo
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_coco,
            nombrePelicula = R.string.coco
        ),
        VideoClubOnlinePeliculasData(
            nombreCategoria = R.string.dibujos,
            imagen = R.drawable.ic_coco, // Temporal: ic_frozen está corrupto
            nombrePelicula = R.string.frozen
        )
    )
)