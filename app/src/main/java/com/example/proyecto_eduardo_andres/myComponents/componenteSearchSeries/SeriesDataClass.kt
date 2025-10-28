package com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries

import com.example.proyecto_eduardo_andres.R

data class SeriesDataClass(
    val nombreSeries: List<VideoClubOnlineDataClass> = listOf(
        //DRAMA
        VideoClubOnlineDataClass("DRAMA",  null, "Mad Men"),
        VideoClubOnlineDataClass("DRAMA", null, "The Wire"),
        VideoClubOnlineDataClass("DRAMA", null, "Better Call Saul"),
        VideoClubOnlineDataClass("DRAMA", null, "This Is Us"),
        VideoClubOnlineDataClass("DRAMA", null, "The Good Wife"),
        VideoClubOnlineDataClass("DRAMA", null, "Euphoria"),
        //ACCION
        VideoClubOnlineDataClass("ACCIÓN", null, "24 Horas"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Prison Break"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Narcos"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Vikings: Valhalla"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Sons of Anarchy"),
        VideoClubOnlineDataClass("ACCIÓN", null, "Strike Back"),
        //TERROR
        VideoClubOnlineDataClass("TERROR", null, "The Haunting of Hill House"),
        VideoClubOnlineDataClass("TERROR", null, "American Horror Story"),
        VideoClubOnlineDataClass("TERROR", null, "The Walking Dead"),
        VideoClubOnlineDataClass("TERROR", null, "Penny Dreadful"),
        VideoClubOnlineDataClass("TERROR", null, "Marianne"),
        VideoClubOnlineDataClass("TERROR", null, "Bates Motel"),
        //DIBUJOS
        VideoClubOnlineDataClass("DIBUJOS", null, "Los Simpson"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Bob Esponja"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Rick y Morty"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Avatar: La Leyenda de Aang"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Hora de Aventuras"),
        VideoClubOnlineDataClass("DIBUJOS", null, "Gravity Falls")
    )
)