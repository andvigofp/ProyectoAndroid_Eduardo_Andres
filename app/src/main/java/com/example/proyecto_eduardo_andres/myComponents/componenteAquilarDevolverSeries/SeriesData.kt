package com.example.proyecto_eduardo_andres.myComponents.componenteAquilarDevolverSeries

data class SeriesData(
    val nombreSeries: List<VideoClubOnlineData> = listOf(
        // DRAMA
        VideoClubOnlineData(null, "Mad Men", "Ambientada en los años 60, sigue la vida de Don Draper, un carismático publicista con un pasado misterioso."),
        VideoClubOnlineData(null, "The Wire", "Un retrato crudo y realista de la ciudad de Baltimore a través de policías, criminales y ciudadanos."),
        VideoClubOnlineData(null, "Better Call Saul", "Precuela de Breaking Bad que muestra la transformación de Jimmy McGill en el abogado Saul Goodman."),
        VideoClubOnlineData(null, "This Is Us", "Una conmovedora historia sobre una familia y cómo sus vidas se entrelazan a lo largo del tiempo."),
        VideoClubOnlineData(null, "The Good Wife", "Tras un escándalo político, Alicia Florrick retoma su carrera como abogada y reconstruye su vida."),
        VideoClubOnlineData(null, "Euphoria", "Un grupo de adolescentes explora la identidad, el amor y la adicción en una serie visualmente impactante."),

        // ACCIÓN
        VideoClubOnlineData(null, "24 Horas", "Jack Bauer debe detener ataques terroristas en tiempo real, con cada episodio representando una hora del día."),
        VideoClubOnlineData(null, "Prison Break", "Un hombre idea un plan para sacar a su hermano inocente del corredor de la muerte."),
        VideoClubOnlineData(null, "Narcos", "Basada en hechos reales, narra el ascenso y caída de Pablo Escobar y el narcotráfico en Colombia."),
        VideoClubOnlineData(null, "Vikings: Valhalla", "Secuela de Vikings que sigue las aventuras de los héroes nórdicos Leif Erikson y Freydis Eiriksdottir."),
        VideoClubOnlineData(null, "Sons of Anarchy", "La vida de un club de motociclistas que opera en el mundo del crimen y la lealtad familiar."),
        VideoClubOnlineData(null, "Strike Back", "Dos agentes de élite se enfrentan a peligrosas misiones alrededor del mundo para detener amenazas globales."),

        // TERROR
        VideoClubOnlineData(null, "The Haunting of Hill House", "Una familia se enfrenta a los traumas y fantasmas de su pasado en una casa maldita."),
        VideoClubOnlineData(null, "American Horror Story", "Cada temporada presenta una historia diferente llena de terror, misterio y oscuridad."),
        VideoClubOnlineData(null, "The Walking Dead", "Un grupo de supervivientes lucha por su vida en un mundo devastado por zombis."),
        VideoClubOnlineData(null, "Penny Dreadful", "Reúne personajes clásicos del terror en una historia gótica llena de suspense y tragedia."),
        VideoClubOnlineData(null, "Marianne", "Una escritora de terror descubre que sus pesadillas se vuelven realidad cuando un demonio la persigue."),
        VideoClubOnlineData(null, "Bates Motel", "Explora la juventud de Norman Bates y su compleja relación con su madre antes de los eventos de 'Psicosis'."),

        // DIBUJOS
        VideoClubOnlineData(null, "Los Simpson", "Sátira animada sobre una familia de clase media en la ciudad de Springfield."),
        VideoClubOnlineData(null, "Bob Esponja", "Un optimista habitante del fondo del mar vive divertidas aventuras en Fondo de Bikini."),
        VideoClubOnlineData(null, "Rick y Morty", "Un científico excéntrico y su nieto viajan por universos paralelos en absurdas aventuras de ciencia ficción."),
        VideoClubOnlineData(null, "Avatar: La Leyenda de Aang", "Un joven Avatar debe dominar los cuatro elementos para restaurar el equilibrio en el mundo."),
        VideoClubOnlineData(null, "Hora de Aventuras", "Finn y su perro mágico Jake viven extravagantes aventuras en la Tierra de Ooo."),
        VideoClubOnlineData(null, "Gravity Falls", "Dos hermanos descubren los misterios sobrenaturales de un extraño pueblo durante sus vacaciones.")
    )
)