package com.example.proyecto_eduardo_andres.myComponents.componenteSearchPeliculas

import com.example.proyecto_eduardo_andres.R

data class PeliculasData(
    val nombrePeliculas: List<VideoClubOnlineSearchPeliculasData> = listOf(
        //DRAMA
        VideoClubOnlineSearchPeliculasData("DRAMA", R.drawable.ic_cadena_perpetua, "Cadena perpetua"),
        VideoClubOnlineSearchPeliculasData("DRAMA", R.drawable.ic_big_fish, "Big Fish"),
        VideoClubOnlineSearchPeliculasData("DRAMA", R.drawable.ic_padrino, "El Padrino"),
        VideoClubOnlineSearchPeliculasData("DRAMA", R.drawable.ic_lista_scindler, "La lista de Scindler"),
        VideoClubOnlineSearchPeliculasData("DRAMA", R.drawable.ic_nunca_jamas, "Descubriendo Nunca jamás"),
        VideoClubOnlineSearchPeliculasData("DRAMA", R.drawable.ic_vida_bella, "La Vida es Bella"),
        //ACCION
        VideoClubOnlineSearchPeliculasData("ACCIÓN", null, "Mad Max"),
        VideoClubOnlineSearchPeliculasData("ACCIÓN", null, "John Wick"),
        VideoClubOnlineSearchPeliculasData("ACCIÓN", null, "Misión Imposible"),
        VideoClubOnlineSearchPeliculasData("ACCIÓN", null, "Gladiator"),
        VideoClubOnlineSearchPeliculasData("ACCIÓN", null, "Terminator 2"),
        VideoClubOnlineSearchPeliculasData("ACCIÓN", null, "Die Hard"),
        //TERROR
        VideoClubOnlineSearchPeliculasData("TERROR", null, "El Conjuro"),
        VideoClubOnlineSearchPeliculasData("TERROR", null, "It"),
        VideoClubOnlineSearchPeliculasData("TERROR", null, "Siniestro"),
        VideoClubOnlineSearchPeliculasData("TERROR", null, "La Monja"),
        VideoClubOnlineSearchPeliculasData("TERROR", null, "Viernes 13"),
        VideoClubOnlineSearchPeliculasData("TERROR", null, "Pesadilla en Elm Street"),
        //DIBUJOS
        VideoClubOnlineSearchPeliculasData("DIBUJOS", null, "Toy Story"),
        VideoClubOnlineSearchPeliculasData("DIBUJOS", null, "Up"),
        VideoClubOnlineSearchPeliculasData("DIBUJOS", null, "Shrek"),
        VideoClubOnlineSearchPeliculasData("DIBUJOS", null, "Buscando a Nemo"),
        VideoClubOnlineSearchPeliculasData("DIBUJOS", null, "Coco"),
        VideoClubOnlineSearchPeliculasData("DIBUJOS", null, "Frozen")
    )
)