package com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas

// Clase que contiene la lista de películas
data class PeliculasData(
    val nombrePeliculas: List<VideoClubOnlineAlquilarPeliculasData> = listOf(
        // DRAMA
        VideoClubOnlineAlquilarPeliculasData(null, "Cadena perpetua", "Un banquero inocente es condenado a cadena perpetua y forja una amistad inolvidable."),
        VideoClubOnlineAlquilarPeliculasData(null, "Big Fish", "Un hijo intenta descubrir la verdad detrás de las historias de su padre."),
        VideoClubOnlineAlquilarPeliculasData(null, "El Padrino", "La historia de una poderosa familia mafiosa italiana en Nueva York."),
        VideoClubOnlineAlquilarPeliculasData(null, "La lista de Schindler", "Un empresario alemán salva a cientos de judíos durante el Holocausto."),
        VideoClubOnlineAlquilarPeliculasData(null, "Descubriendo Nunca Jamás", "El escritor de Peter Pan se inspira en una familia real."),
        VideoClubOnlineAlquilarPeliculasData(null, "La Vida es Bella", "Un padre usa el humor para proteger a su hijo en un campo de concentración."),

        //ACCIÓN
        VideoClubOnlineAlquilarPeliculasData(null, "Mad Max", "En un futuro postapocalíptico, un hombre lucha por sobrevivir."),
        VideoClubOnlineAlquilarPeliculasData(null, "John Wick", "Un exasesino busca venganza después de perder a su perro."),
        VideoClubOnlineAlquilarPeliculasData(null, "Misión Imposible", "Ethan Hunt debe detener una organización criminal."),
        VideoClubOnlineAlquilarPeliculasData(null, "Gladiator", "Un general romano busca venganza como gladiador."),
        VideoClubOnlineAlquilarPeliculasData(null, "Terminator 2", "Un cyborg regresa para proteger al futuro líder de la resistencia."),
        VideoClubOnlineAlquilarPeliculasData(null, "Die Hard", "Un policía lucha contra terroristas en un rascacielos."),

        //TERROR
        VideoClubOnlineAlquilarPeliculasData(null, "El Conjuro", "Una pareja de investigadores paranormales ayuda a una familia aterrorizada."),
        VideoClubOnlineAlquilarPeliculasData(null, "It", "Un grupo de niños enfrenta a una entidad que toma forma de payaso."),
        VideoClubOnlineAlquilarPeliculasData(null, "Siniestro", "Un escritor encuentra videos perturbadores en su nueva casa."),
        VideoClubOnlineAlquilarPeliculasData(null, "La Monja", "Un sacerdote investiga la aparición demoníaca de una monja."),
        VideoClubOnlineAlquilarPeliculasData(null, "Viernes 13", "Un asesino acecha a jóvenes en un campamento."),
        VideoClubOnlineAlquilarPeliculasData(null, "Pesadilla en Elm Street", "Un asesino ataca a sus víctimas en sus sueños."),

        //DIBUJOS
        VideoClubOnlineAlquilarPeliculasData(null, "Toy Story", "Los juguetes cobran vida cuando los humanos no los ven."),
        VideoClubOnlineAlquilarPeliculasData(null, "Up", "Un anciano viaja a Sudamérica en su casa con globos."),
        VideoClubOnlineAlquilarPeliculasData(null, "Shrek", "Un ogro parte en una aventura para rescatar a una princesa."),
        VideoClubOnlineAlquilarPeliculasData(null, "Buscando a Nemo", "Un pez padre busca a su hijo perdido en el océano."),
        VideoClubOnlineAlquilarPeliculasData(null, "Coco", "Un niño viaja al mundo de los muertos para descubrir su historia familiar."),
        VideoClubOnlineAlquilarPeliculasData(null, "Frozen", "Dos hermanas descubren el poder del amor y la magia del hielo.")
    )
)
