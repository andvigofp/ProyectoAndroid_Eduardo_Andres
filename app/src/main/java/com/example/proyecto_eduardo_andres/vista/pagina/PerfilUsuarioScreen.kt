@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.vista.pagina

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.colorAzulOscurso
import com.example.compose.colorVioleta
import com.example.proyecto_eduardo_andres.R
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.AlquilerPeliculasRepositoryInMemory
import com.example.proyecto_eduardo_andres.data.repository.alquilerPeliculasRepository.IAlquilerPeliculasRepository
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.IPerfilUsuarioRepository
import com.example.proyecto_eduardo_andres.data.repository.perfilRepositorio.PerfilUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.modelo.PerfilUsuarioButtonTextsDto
import com.example.proyecto_eduardo_andres.modelo.PerfilUsuarioDto
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.viewmodel.vm.PerfilUsuarioViewModel
import com.example.proyecto_eduardo_andres.viewmodel.vm.PerfilUsuarioViewModelFactory
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.ConfirmationDialog
import com.example.proyecto_eduardo_andres.vista.componente.componenteAlertDialog.InfoDialog
import com.example.proyecto_eduardo_andres.vista.componente.componentePerfilUsuario.CampoPerfilUsuario
import com.example.proyecto_eduardo_andres.vista.componente.componentePerfilUsuario.PerfilUsuarioButtons
import com.example.proyecto_eduardo_andres.vista.componente.componenteToolbar.toolBar

@Composable
fun PerfilUsuarioScreen(
    userId: String,
    alquilerRepository: IAlquilerPeliculasRepository,
    repository: IPerfilUsuarioRepository,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onPeliculaClick: (String) -> Unit
) {


    val viewModel: PerfilUsuarioViewModel = viewModel(
        factory = PerfilUsuarioViewModelFactory(
            userId = userId,
            repository = repository,
            alquilerRepository = alquilerRepository
        )
    )


    val uiState by viewModel.uiState.collectAsState()

    // Llamamos a cargar los datos del usuario cuando se inicia el Composable
    LaunchedEffect(userId) {
        viewModel.cargarUsuario(userId)
    }

    // Recargar películas alquiladas cuando se vuelve a la pantalla
    LaunchedEffect(userId) {
        viewModel.recargarPeliculasAlquiladas(userId)
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceBright)
    ) {
        // --- Degradado superior ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    Brush.linearGradient(
                        colors = listOf(colorVioleta, colorAzulOscurso),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f)
                    )
                )
        )

        // --- Degradado inferior ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(listOf(colorVioleta, colorAzulOscurso))
                )
        )

        // --- Contenido principal ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 160.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tarjeta blanca central con contenido scrolleable
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .shadow(12.dp, RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Imagen circular
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .shadow(8.dp, CircleShape)
                        .background(colorAzulOscurso, CircleShape)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logotipo_team),
                        contentDescription = stringResource(R.string.logo),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campos de perfil
                CampoPerfilUsuario(
                    perfilUsarioData = PerfilUsuarioDto(
                        nombreUsuaro = uiState.nombreUsuario,
                        email = uiState.email,
                        password = uiState.password
                    ),
                    onPerfilUsuarioData = {
                        viewModel.onNombreUsuarioChange(it.nombreUsuaro)
                        viewModel.onEmailChange(it.email)
                        viewModel.onPasswordChange(it.password)
                    },
                    isEditing = uiState.isEditing // Para habilitar/deshabilitar edición
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Texto adicional
                Text(
                    text = stringResource(R.string.peliculas_alquiladas),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Lista de películas alquiladas
                if (uiState.peliculasAlquiladas.isEmpty()) {
                    Text(
                        text = "No hay películas alquiladas",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.peliculasAlquiladas) { pelicula ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Color.LightGray.copy(alpha = 0.3f),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        onPeliculaClick(pelicula.id)
                                    }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (pelicula.imagen != null) {
                                    Image(
                                        painter = painterResource(id = pelicula.imagen),
                                        contentDescription = stringResource(pelicula.nombre),
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                                Column {
                                    Text(
                                        text = stringResource(pelicula.nombre),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = stringResource(pelicula.categoria),
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Botón fijado en la parte inferior (siempre visible)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp)
            ) {
                PerfilUsuarioButtons(
                    perfilUsuarioButtonTextsData = PerfilUsuarioButtonTextsDto(
                        modificar = if (uiState.isEditing)
                            stringResource(R.string.guardar)
                        else
                            stringResource(R.string.modificar_usuario)
                    ),
                    onModificarUsuario = {
                        if (uiState.isEditing) {
                            viewModel.guardarCambios()
                        } else {
                            viewModel.toggleEditing()
                        }
                    }
                )
            }
        }

        // --- TOOLBAR + título ---
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                toolBar(
                    onBackClick = onBackClick,
                    onHomeClick = onHomeClick,
                    onCameraClick = onCameraClick,
                    onProfileClick = onProfileClick,
                    onLogoutClick = onLogoutClick
                )

                Text(
                    text = stringResource(R.string.datos_usuario),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
        }

        ConfirmationDialog(
            showDialog = uiState.showConfirmacionDialog,
            onDismissRequest = { viewModel.cerrarConfirmacionDialog() },
            title = stringResource(R.string.confirmacion),
            message = stringResource(R.string.confirmacion_datos_guardados),
            confirmButtonText = stringResource(R.string.aceptar),
            dismissButtonText = stringResource(R.string.cancelar),
            onConfirmClick = {
                viewModel.cerrarConfirmacionDialog()
                // Aquí podrías agregar alguna acción extra si quieres
            },
            onDismissClick = { viewModel.cerrarConfirmacionDialog() }
        )


        // --- AlertDialog de confirmación ---
        InfoDialog(
            showDialog = uiState.showInfoDialog,
            onDismissRequest = { viewModel.cerrarInfoDialog() },
            title = uiState.infoDialogTitle,
            message = uiState.infoDialogMessage
        )
    }
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilUsuarioScreenPreview() {

    val alquilerRepository = AlquilerPeliculasRepositoryInMemory()

    val repository = PerfilUsuarioRepositoryInMemory(
        apiService = RetrofitClient.usuarioApiService
    )

    MaterialTheme {
        PerfilUsuarioScreen(
            userId = "1",
            repository = repository,
            alquilerRepository = alquilerRepository,
            onBackClick = {},
            onHomeClick = {},
            onCameraClick = {},
            onProfileClick = {},
            onLogoutClick = {},
            onPeliculaClick = {}
        )
    }
}

