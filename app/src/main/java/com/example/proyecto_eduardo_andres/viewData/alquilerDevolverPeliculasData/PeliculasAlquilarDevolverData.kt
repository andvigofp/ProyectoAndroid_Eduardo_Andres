package com.example.proyecto_eduardo_andres.viewData.alquilerDevolverPeliculasData

import com.example.proyecto_eduardo_andres.R

// Clase que contiene la lista de películas
data class PeliculasAlquilarDevolverData(
    val nombrePeliculas: List<VideoClubOnlineAlquilarPeliculasUiState> = listOf(
        // DRAMA
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_cadena_perpetua,
            nombrePelicula = R.string.cadena_perpetua,
            descripcion = R.string.banquero_inocente
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_big_fish,
            nombrePelicula = R.string.big_fish,
            descripcion = R.string.un_hijo_padre
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_padrino,
            nombrePelicula = R.string.el_padrino,
            descripcion = R.string.familia_mafiosa
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_lista_scindler,
            nombrePelicula = R.string.la_lista_schindler,
            descripcion = R.string.salva_cientos_judios
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_nunca_jamas,
            nombrePelicula = R.string.descubriendo_nunca_jamas,
            descripcion = R.string.peter_pan
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_vida_bella,
            nombrePelicula = R.string.la_vida_es_bella,
            descripcion = R.string.padre_protege_hijo
        ),

        //ACCIÓN
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_mad_max,
            nombrePelicula = R.string.mad_max,
            descripcion = R.string.futuro_postapocaliptico
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_jhon_wick,
            nombrePelicula = R.string.john_wick,
            descripcion = R.string.exassino_busca_venganza
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_mision_imposible,
            nombrePelicula = R.string.mision_imposible,
            descripcion = R.string.organizacion_criminal
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_gladiator,
            nombrePelicula = R.string.gladiator,
            descripcion = R.string.gladiador_busca_venganza
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_terminator_dos,
            nombrePelicula = R.string.terminator_dos,
            descripcion = R.string.cybor_protege_futuro
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_die_hard,
            nombrePelicula = R.string.die_hard,
            descripcion = R.string.policia_lucha_terroristas
        ),

        //TERROR
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_el_conjuro,
            nombrePelicula = R.string.el_conjuro,
            descripcion = R.string.pareja_investiga_paranormales
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_it,
            nombrePelicula = R.string.it,
            descripcion = R.string.niños_enfrenta_payaso
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_siniestro,
            nombrePelicula = R.string.siniestro,
            descripcion = R.string.escritor_encuentra_videos_perturbados
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_la_monja,
            nombrePelicula = R.string.la_monja,
            descripcion = R.string.monja_ivestiga_aparcion_demoniaca
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_viernes_trece,
            nombrePelicula = R.string.viernes_trece,
            descripcion = R.string.asesino_acecha_jovenes_campamento
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_pesadialla_calle_elm,
            nombrePelicula = R.string.pesadilla_en_elm_street,
            descripcion = R.string.asesino_ataca_en_sueños
        ),

        //DIBUJOS
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_toy_story,
            nombrePelicula = R.string.toy_story,
            descripcion = R.string.jueguetes_cobran_vida_humanos_no_ven
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_up,
            nombrePelicula = R.string.up,
            descripcion = R.string.anciano_viaja_casa_globos
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_shreck,
            nombrePelicula = R.string.shrek,
            descripcion = R.string.ogro_rescata_princesa
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_buscando_nemo,
            nombrePelicula = R.string.buscando_a_nemo,
            descripcion = R.string.pez_busca_hijo_oceano
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_coco,
            nombrePelicula = R.string.coco,
            descripcion = R.string.niño_viaja_mundo_muertos
        ),
        VideoClubOnlineAlquilarPeliculasUiState(
            imagen = R.drawable.ic_frozen,
            nombrePelicula = R.string.frozen,
            descripcion = R.string.dos_hermas_poder_amor_magia
        )
    )
)