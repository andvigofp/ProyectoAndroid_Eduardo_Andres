package com.example.proyecto_eduardo_andres.myComponents.componenteVideoClubOnlieSeries

import com.example.proyecto_eduardo_andres.R

data class SeriesData(
    val nombreSeries: List<VideoClubOnlineData> = listOf(
        //DRAMA
        VideoClubOnlineData("DRAMA", R.drawable.ic_mad_men, "Mad Men"),
        VideoClubOnlineData("DRAMA", R.drawable.ic_the_wire, "The Wire"),
        VideoClubOnlineData("DRAMA", R.drawable.ic_better_call_saul, "Better Call Saul"),
        VideoClubOnlineData("DRAMA", R.drawable.ic_this_is_us, "This Is Us"),
        VideoClubOnlineData("DRAMA", R.drawable.the_good_wife, "The Good Wife"),
        VideoClubOnlineData("DRAMA", R.drawable.ic_euphoria, "Euphoria"),
        //ACCION
        VideoClubOnlineData("ACCIÓN", R.drawable.ic_24_horas, "24 Horas"),
        VideoClubOnlineData("ACCIÓN", R.drawable.ic_prision_break, "Prison Break"),
        VideoClubOnlineData("ACCIÓN", R.drawable.ic_narcos, "Narcos"),
        VideoClubOnlineData("ACCIÓN", R.drawable.ic_vikings_vahalla, "Vikings: Valhalla"),
        VideoClubOnlineData("ACCIÓN", R.drawable.ic_sons_of_anarchy, "Sons of Anarchy"),
        VideoClubOnlineData("ACCIÓN", R.drawable.ic_stricke_back, "Strike Back"),
        //TERROR
        VideoClubOnlineData("TERROR", R.drawable.ic_the_haunting_of_hill_house, "The Haunting of Hill House"),
        VideoClubOnlineData("TERROR", R.drawable.ic_american_horror_story, "American Horror Story"),
        VideoClubOnlineData("TERROR", R.drawable.ic_the_walking_dead, "The Walking Dead"),
        VideoClubOnlineData("TERROR", R.drawable.ic_penny_dreadful, "Penny Dreadful"),
        VideoClubOnlineData("TERROR", R.drawable.ic_mariane, "Marianne"),
        VideoClubOnlineData("TERROR", R.drawable.ic_bates_motel, "Bates Motel"),
        //DIBUJOS
        VideoClubOnlineData("DIBUJOS", R.drawable.ic_los_simpson, "Los Simpson"),
        VideoClubOnlineData("DIBUJOS", R.drawable.ic_bod_esponja, "Bob Esponja"),
        VideoClubOnlineData("DIBUJOS", R.drawable.ic_rick_morty, "Rick y Morty"),
        VideoClubOnlineData("DIBUJOS", R.drawable.ic_avatar_la_leyenda_de_aang, "Avatar: La Leyenda de Aang"),
        VideoClubOnlineData("DIBUJOS", R.drawable.ic_hora_aventuras, "Hora de Aventuras"),
        VideoClubOnlineData("DIBUJOS", R.drawable.ic_gravity_falls, "Gravity Falls")
    )
)