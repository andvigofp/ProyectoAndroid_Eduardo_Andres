package com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries

import com.example.proyecto_eduardo_andres.R

data class SeriesAlquilerDevolverData(
    val nombreSeries: List<VideoClubOnlineAlquilerSeriesData> = listOf(
        // DRAMA
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_mad_men, "Mad Men", "Ambientada en los años 60, sigue la vida de Don Draper, un carismático publicista con un pasado misterioso."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_the_wire, "The Wire", "Un retrato crudo y realista de la ciudad de Baltimore a través de policías, criminales y ciudadanos."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_better_call_saul, "Better Call Saul", "Precuela de Breaking Bad que muestra la transformación de Jimmy McGill en el abogado Saul Goodman."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_this_is_us, "This Is Us", "Una conmovedora historia sobre una familia y cómo sus vidas se entrelazan a lo largo del tiempo."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.the_good_wife, "The Good Wife", "Tras un escándalo político, Alicia Florrick retoma su carrera como abogada y reconstruye su vida."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_euphoria, "Euphoria", "Un grupo de adolescentes explora la identidad, el amor y la adicción en una serie visualmente impactante."),

        // ACCIÓN
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_24_horas, "24 Horas", "Jack Bauer debe detener ataques terroristas en tiempo real, con cada episodio representando una hora del día."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_prision_break, "Prison Break", "Un hombre idea un plan para sacar a su hermano inocente del corredor de la muerte."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_narcos, "Narcos", "Basada en hechos reales, narra el ascenso y caída de Pablo Escobar y el narcotráfico en Colombia."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_vikings_vahalla, "Vikings: Valhalla", "Secuela de Vikings que sigue las aventuras de los héroes nórdicos Leif Erikson y Freydis Eiriksdottir."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_sons_of_anarchy, "Sons of Anarchy", "La vida de un club de motociclistas que opera en el mundo del crimen y la lealtad familiar."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_stricke_back, "Strike Back", "Dos agentes de élite se enfrentan a peligrosas misiones alrededor del mundo para detener amenazas globales."),

        // TERROR
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_the_haunting_of_hill_house, "The Haunting of Hill House", "Una familia se enfrenta a los traumas y fantasmas de su pasado en una casa maldita."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_american_horror_story, "American Horror Story", "Cada temporada presenta una historia diferente llena de terror, misterio y oscuridad."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_the_walking_dead, "The Walking Dead", "Un grupo de supervivientes lucha por su vida en un mundo devastado por zombis."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_penny_dreadful, "Penny Dreadful", "Reúne personajes clásicos del terror en una historia gótica llena de suspense y tragedia."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_mariane, "Marianne", "Una escritora de terror descubre que sus pesadillas se vuelven realidad cuando un demonio la persigue."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_bates_motel, "Bates Motel", "Explora la juventud de Norman Bates y su compleja relación con su madre antes de los eventos de 'Psicosis'."),

        // DIBUJOS
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_los_simpson, "Los Simpson", "Sátira animada sobre una familia de clase media en la ciudad de Springfield."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_bod_esponja, "Bob Esponja", "Un optimista habitante del fondo del mar vive divertidas aventuras en Fondo de Bikini."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_rick_morty, "Rick y Morty", "Un científico excéntrico y su nieto viajan por universos paralelos en absurdas aventuras de ciencia ficción."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_avatar_la_leyenda_de_aang, "Avatar: La Leyenda de Aang", "Un joven Avatar debe dominar los cuatro elementos para restaurar el equilibrio en el mundo."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_hora_aventuras, "Hora de Aventuras", "Finn y su perro mágico Jake viven extravagantes aventuras en la Tierra de Ooo."),
        VideoClubOnlineAlquilerSeriesData(R.drawable.ic_gravity_falls, "Gravity Falls", "Dos hermanos descubren los misterios sobrenaturales de un extraño pueblo durante sus vacaciones.")
    )
)