package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubListaPeliculas

import com.example.proyecto_eduardo_andres.R

data class PeliculasDataClass(
    val nombrePeliculas: List<VideoClubOnlinePeliculasData> = listOf(
        //DRAMA
        VideoClubOnlinePeliculasData("DRAMA",  R.drawable.ic_cadena_perpetua, "Cadena perpetua"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_big_fish, "Big Fish"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_padrino, "El Padrino"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_lista_scindler, "La lista de Scindler"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_nunca_jamas, "Descubriendo Nunca jamás"),
        VideoClubOnlinePeliculasData("DRAMA", R.drawable.ic_vida_bella, "La Vida es Bella"),
        //ACCION
        VideoClubOnlinePeliculasData("ACCIÓN", null, "Mad Max"),
        VideoClubOnlinePeliculasData("ACCIÓN", null, "John Wick"),
        VideoClubOnlinePeliculasData("ACCIÓN", null, "Misión Imposible"),
        VideoClubOnlinePeliculasData("ACCIÓN", null, "Gladiator"),
        VideoClubOnlinePeliculasData("ACCIÓN", null, "Terminator 2"),
        VideoClubOnlinePeliculasData("ACCIÓN", null, "Die Hard"),
        //TERROR
        VideoClubOnlinePeliculasData("TERROR", null, "El Conjuro"),
        VideoClubOnlinePeliculasData("TERROR", null, "It"),
        VideoClubOnlinePeliculasData("TERROR", null, "Siniestro"),
        VideoClubOnlinePeliculasData("TERROR", null, "La Monja"),
        VideoClubOnlinePeliculasData("TERROR", null, "Viernes 13"),
        VideoClubOnlinePeliculasData("TERROR", null, "Pesadilla en Elm Street"),
        //DIBUJOS
        VideoClubOnlinePeliculasData("DIBUJOS", null, "Toy Story"),
        VideoClubOnlinePeliculasData("DIBUJOS", null, "Up"),
        VideoClubOnlinePeliculasData("DIBUJOS", null, "Shrek"),
        VideoClubOnlinePeliculasData("DIBUJOS", null, "Buscando a Nemo"),
        VideoClubOnlinePeliculasData("DIBUJOS", null, "Coco"),
        VideoClubOnlinePeliculasData("DIBUJOS", null, "Frozen")
    )
)