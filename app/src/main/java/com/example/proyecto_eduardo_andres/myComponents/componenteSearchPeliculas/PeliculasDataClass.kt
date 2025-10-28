package com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas

import com.example.proyecto_eduardo_andres.R

data class PeliculasDataClass(
    val nombrePeliculas: List<VideoClubOnlineDataClass> = listOf(
        //DRAMA
        VideoClubOnlineDataClass("DRAMA", R.drawable.ic_cadena_perpetua, "Cadena perpetua"),
        VideoClubOnlineDataClass("DRAMA", R.drawable.ic_big_fish, "Big Fish"),
        VideoClubOnlineDataClass("DRAMA", R.drawable.ic_padrino, "El Padrino"),
        VideoClubOnlineDataClass("DRAMA", R.drawable.ic_lista_scindler, "La lista de Scindler"),
        VideoClubOnlineDataClass("DRAMA", R.drawable.ic_nunca_jamas, "Descubriendo Nunca jamás"),
        VideoClubOnlineDataClass("DRAMA", R.drawable.ic_vida_bella, "La Vida es Bella"),
        //ACCION
        VideoClubOnlineDataClass("ACCIÓN", null, "Mad Max"),
        VideoClubOnlineDataClass("ACCIÓN", null, "John Wick"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Misión Imposible"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Gladiator"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Terminator 2"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Die Hard"),
        //TERROR
        VideoClubOnlineDataClass("TERROR", null, "El Conjuro"),
        VideoClubOnlineDataClass("TERROR", null, "It"),
        VideoClubOnlineDataClass("TERROR", null, "Siniestro"),
        VideoClubOnlineDataClass("TERROR", null, "La Monja"),
        VideoClubOnlineDataClass("TERROR", null, "Viernes 13"),
        VideoClubOnlineDataClass("TERROR", null, "Pesadilla en Elm Street"),
        //DIBUJOS
        VideoClubOnlineDataClass("DIBUJOS", null, "Toy Story"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Up"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Shrek"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Buscando a Nemo"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Coco"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Frozen")
    )
)