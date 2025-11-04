package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas

import com.example.proyecto_eduardo_andres.R

data class PeliculasData(
    val nombrePeliculas: List<VideoClubOnlinePeliculasData> = listOf(
        //DRAMA
        VideoClubOnlinePeliculasData("DRAMA",  R.drawable.ic_cadena_perpetua, "Cadena perpetua"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_big_fish, "Big Fish"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_padrino, "El Padrino"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_lista_scindler, "La lista de Scindler"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_nunca_jamas, "Descubriendo Nunca jamás"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_vida_bella, "La Vida es Bella"),
        //ACCION
        VideoClubOnlinePeliculasData("ACCIÓN", R.drawable.ic_mad_max, "Mad Max"),
        VideoClubOnlinePeliculasData("ACCIÓN", R.drawable.ic_jhon_wick, "John Wick"),
        VideoClubOnlinePeliculasData("ACCIÓN", R.drawable.ic_mision_imposible, "Misión Imposible"),
        VideoClubOnlinePeliculasData("ACCIÓN", R.drawable.ic_gladiator, "Gladiator"),
        VideoClubOnlinePeliculasData("ACCIÓN", R.drawable.ic_terminator_dos, "Terminator 2"),
        VideoClubOnlinePeliculasData("ACCIÓN", R.drawable.ic_die_hard, "Die Hard"),
        //TERROR
        VideoClubOnlinePeliculasData("TERROR", R.drawable.ic_el_conjuro, "El Conjuro"),
        VideoClubOnlinePeliculasData("TERROR", R.drawable.ic_it, "It"),
        VideoClubOnlinePeliculasData("TERROR", R.drawable.ic_siniestro, "Siniestro"),
        VideoClubOnlinePeliculasData("TERROR", R.drawable.ic_la_monja, "La Monja"),
        VideoClubOnlinePeliculasData("TERROR", R.drawable.ic_viernes_trece, "Viernes 13"),
        VideoClubOnlinePeliculasData("TERROR", R.drawable.ic_pesadialla_calle_elm, "Pesadilla en Elm Street"),
        //DIBUJOS
        VideoClubOnlinePeliculasData("DIBUJOS", R.drawable.ic_toy_story, "Toy Story"),
        VideoClubOnlinePeliculasData("DIBUJOS", R.drawable.ic_up, "Up"),
        VideoClubOnlinePeliculasData("DIBUJOS", R.drawable.ic_shreck, "Shrek"),
        VideoClubOnlinePeliculasData("DIBUJOS", R.drawable.ic_buscando_nemo, "Buscando a Nemo"),
        VideoClubOnlinePeliculasData("DIBUJOS", R.drawable.ic_coco, "Coco"),
        VideoClubOnlinePeliculasData("DIBUJOS", R.drawable.ic_frozen, "Frozen")
    )
)