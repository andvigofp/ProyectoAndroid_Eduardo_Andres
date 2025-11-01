package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubOnlieSeries

data class SeriesData(
    val nombreSeries: List<VideoClubOnlineData> = listOf(
        //DRAMA
        VideoClubOnlineData("DRAMA",  null, "Mad Men"),
        VideoClubOnlineData("DRAMA", null, "The Wire"),
        VideoClubOnlineData("DRAMA", null, "Better Call Saul"),
        VideoClubOnlineData("DRAMA", null, "This Is Us"),
        VideoClubOnlineData("DRAMA", null, "The Good Wife"),
        VideoClubOnlineData("DRAMA", null, "Euphoria"),
        //ACCION
        VideoClubOnlineData("ACCIÓN", null, "24 Horas"),
        VideoClubOnlineData("ACCIÓN", null, "Prison Break"),
        VideoClubOnlineData("ACCIÓN", null, "Narcos"),
        VideoClubOnlineData("ACCIÓN", null, "Vikings: Valhalla"),
        VideoClubOnlineData("ACCIÓN", null, "Sons of Anarchy"),
        VideoClubOnlineData("ACCIÓN", null, "Strike Back"),
        //TERROR
        VideoClubOnlineData("TERROR", null, "The Haunting of Hill House"),
        VideoClubOnlineData("TERROR", null, "American Horror Story"),
        VideoClubOnlineData("TERROR", null, "The Walking Dead"),
        VideoClubOnlineData("TERROR", null, "Penny Dreadful"),
        VideoClubOnlineData("TERROR", null, "Marianne"),
        VideoClubOnlineData("TERROR", null, "Bates Motel"),
        //DIBUJOS
        VideoClubOnlineData("DIBUJOS", null, "Los Simpson"),
        VideoClubOnlineData("DIBUJOS", null, "Bob Esponja"),
        VideoClubOnlineData("DIBUJOS", null, "Rick y Morty"),
        VideoClubOnlineData("DIBUJOS", null, "Avatar: La Leyenda de Aang"),
        VideoClubOnlineData("DIBUJOS", null, "Hora de Aventuras"),
        VideoClubOnlineData("DIBUJOS", null, "Gravity Falls")
    )
)