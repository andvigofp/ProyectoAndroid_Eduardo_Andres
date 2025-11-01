package com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas

// Clase que contiene la lista de películas
data class PeliculasData(
    val nombrePeliculas: List<VideoClubOnlineDataClass> = listOf(
        // DRAMA
        VideoClubOnlineDataClass(null, "Cadena perpetua", "Un banquero inocente es condenado a cadena perpetua y forja una amistad inolvidable."),
        VideoClubOnlineDataClass(null, "Big Fish", "Un hijo intenta descubrir la verdad detrás de las historias de su padre."),
        VideoClubOnlineDataClass(null, "El Padrino", "La historia de una poderosa familia mafiosa italiana en Nueva York."),
        VideoClubOnlineDataClass(null, "La lista de Schindler", "Un empresario alemán salva a cientos de judíos durante el Holocausto."),
        VideoClubOnlineDataClass(null, "Descubriendo Nunca Jamás", "El escritor de Peter Pan se inspira en una familia real."),
        VideoClubOnlineDataClass(null, "La Vida es Bella", "Un padre usa el humor para proteger a su hijo en un campo de concentración."),

        //ACCIÓN
        VideoClubOnlineDataClass(null, "Mad Max", "En un futuro postapocalíptico, un hombre lucha por sobrevivir."),
        VideoClubOnlineDataClass(null, "John Wick", "Un exasesino busca venganza después de perder a su perro."),
        VideoClubOnlineDataClass(null, "Misión Imposible", "Ethan Hunt debe detener una organización criminal."),
        VideoClubOnlineDataClass(null, "Gladiator", "Un general romano busca venganza como gladiador."),
        VideoClubOnlineDataClass(null, "Terminator 2", "Un cyborg regresa para proteger al futuro líder de la resistencia."),
        VideoClubOnlineDataClass(null, "Die Hard", "Un policía lucha contra terroristas en un rascacielos."),

        //TERROR
        VideoClubOnlineDataClass(null, "El Conjuro", "Una pareja de investigadores paranormales ayuda a una familia aterrorizada."),
        VideoClubOnlineDataClass(null, "It", "Un grupo de niños enfrenta a una entidad que toma forma de payaso."),
        VideoClubOnlineDataClass(null, "Siniestro", "Un escritor encuentra videos perturbadores en su nueva casa."),
        VideoClubOnlineDataClass(null, "La Monja", "Un sacerdote investiga la aparición demoníaca de una monja."),
        VideoClubOnlineDataClass(null, "Viernes 13", "Un asesino acecha a jóvenes en un campamento."),
        VideoClubOnlineDataClass(null, "Pesadilla en Elm Street", "Un asesino ataca a sus víctimas en sus sueños."),

        //DIBUJOS
        VideoClubOnlineDataClass(null, "Toy Story", "Los juguetes cobran vida cuando los humanos no los ven."),
        VideoClubOnlineDataClass(null, "Up", "Un anciano viaja a Sudamérica en su casa con globos."),
        VideoClubOnlineDataClass(null, "Shrek", "Un ogro parte en una aventura para rescatar a una princesa."),
        VideoClubOnlineDataClass(null, "Buscando a Nemo", "Un pez padre busca a su hijo perdido en el océano."),
        VideoClubOnlineDataClass(null, "Coco", "Un niño viaja al mundo de los muertos para descubrir su historia familiar."),
        VideoClubOnlineDataClass(null, "Frozen", "Dos hermanas descubren el poder del amor y la magia del hielo.")
    )
)
