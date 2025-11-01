package com.example.proyecto_eduardo_andres.myComponents.componenteSearchSeries

data class SeriesData(
    val nombreSeries: List<VideoClubOnlineSearchSeriesData> = listOf(
        //DRAMA
        VideoClubOnlineSearchSeriesData("DRAMA",  null, "Mad Men"),
        VideoClubOnlineSearchSeriesData("DRAMA", null, "The Wire"),
        VideoClubOnlineSearchSeriesData("DRAMA", null, "Better Call Saul"),
        VideoClubOnlineSearchSeriesData("DRAMA", null, "This Is Us"),
        VideoClubOnlineSearchSeriesData("DRAMA", null, "The Good Wife"),
        VideoClubOnlineSearchSeriesData("DRAMA", null, "Euphoria"),
        //ACCION
        VideoClubOnlineSearchSeriesData("ACCIÓN", null, "24 Horas"),
        VideoClubOnlineSearchSeriesData("ACCIÓN", null, "Prison Break"),
        VideoClubOnlineSearchSeriesData("ACCIÓN", null, "Narcos"),
        VideoClubOnlineSearchSeriesData("ACCIÓN", null, "Vikings: Valhalla"),
        VideoClubOnlineSearchSeriesData("ACCIÓN", null, "Sons of Anarchy"),
        VideoClubOnlineSearchSeriesData("ACCIÓN", null, "Strike Back"),
        //TERROR
        VideoClubOnlineSearchSeriesData("TERROR", null, "The Haunting of Hill House"),
        VideoClubOnlineSearchSeriesData("TERROR", null, "American Horror Story"),
        VideoClubOnlineSearchSeriesData("TERROR", null, "The Walking Dead"),
        VideoClubOnlineSearchSeriesData("TERROR", null, "Penny Dreadful"),
        VideoClubOnlineSearchSeriesData("TERROR", null, "Marianne"),
        VideoClubOnlineSearchSeriesData("TERROR", null, "Bates Motel"),
        //DIBUJOS
        VideoClubOnlineSearchSeriesData("DIBUJOS", null, "Los Simpson"),
        VideoClubOnlineSearchSeriesData("DIBUJOS", null, "Bob Esponja"),
        VideoClubOnlineSearchSeriesData("DIBUJOS", null, "Rick y Morty"),
        VideoClubOnlineSearchSeriesData("DIBUJOS", null, "Avatar: La Leyenda de Aang"),
        VideoClubOnlineSearchSeriesData("DIBUJOS", null, "Hora de Aventuras"),
        VideoClubOnlineSearchSeriesData("DIBUJOS", null, "Gravity Falls")
    )
)