package com.example.proyecto_eduardo_andres.myComponents.componenteAlquilarDevolverPeliculas

import com.example.proyecto_eduardo_andres.R

// Clase que contiene la lista de películas
data class PeliculasAlquilarDevolverData(
    val nombrePeliculas: List<VideoClubOnlineAlquilarPeliculasData> = listOf(
        // DRAMA
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_cadena_perpetua, "Cadena perpetua", "Un banquero inocente es condenado a cadena perpetua y forja una amistad inolvidable."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_big_fish, "Big Fish", "Un hijo intenta descubrir la verdad detrás de las historias de su padre."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_padrino, "El Padrino", "La historia de una poderosa familia mafiosa italiana en Nueva York."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_lista_scindler, "La lista de Schindler", "Un empresario alemán salva a cientos de judíos durante el Holocausto."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_nunca_jamas, "Descubriendo Nunca Jamás", "El escritor de Peter Pan se inspira en una familia real."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_vida_bella, "La Vida es Bella", "Un padre usa el humor para proteger a su hijo en un campo de concentración."),

        //ACCIÓN
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_mad_max, "Mad Max", "En un futuro postapocalíptico, un hombre lucha por sobrevivir."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_jhon_wick, "John Wick", "Un exasesino busca venganza después de perder a su perro."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_mision_imposible, "Misión Imposible", "Ethan Hunt debe detener una organización criminal."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_gladiator, "Gladiator", "Un general romano busca venganza como gladiador."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_terminator_dos, "Terminator 2", "Un cyborg regresa para proteger al futuro líder de la resistencia."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_die_hard, "Die Hard", "Un policía lucha contra terroristas en un rascacielos."),

        //TERROR
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_el_conjuro, "El Conjuro", "Una pareja de investigadores paranormales ayuda a una familia aterrorizada."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_it, "It", "Un grupo de niños enfrenta a una entidad que toma forma de payaso."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_siniestro, "Siniestro", "Un escritor encuentra videos perturbadores en su nueva casa."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_la_monja, "La Monja", "Un sacerdote investiga la aparición demoníaca de una monja."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_viernes_trece, "Viernes 13", "Un asesino acecha a jóvenes en un campamento."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_pesadialla_calle_elm, "Pesadilla en Elm Street", "Un asesino ataca a sus víctimas en sus sueños."),

        //DIBUJOS
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_toy_story, "Toy Story", "Los juguetes cobran vida cuando los humanos no los ven."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_up, "Up", "Un anciano viaja a Sudamérica en su casa con globos."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_shreck, "Shrek", "Un ogro parte en una aventura para rescatar a una princesa."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_buscando_nemo, "Buscando a Nemo", "Un pez padre busca a su hijo perdido en el océano."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_coco, "Coco", "Un niño viaja al mundo de los muertos para descubrir su historia familiar."),
        VideoClubOnlineAlquilarPeliculasData(R.drawable.ic_frozen, "Frozen", "Dos hermanas descubren el poder del amor y la magia del hielo.")
    )
)
