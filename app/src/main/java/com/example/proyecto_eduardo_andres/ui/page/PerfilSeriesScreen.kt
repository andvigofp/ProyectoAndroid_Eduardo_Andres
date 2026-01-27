@file:Suppress("ALL")

package com.example.proyecto_eduardo_andres.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.proyecto_eduardo_andres.myComponents.componentePerfilUsuario.CampoPerfilUsuario
import com.example.proyecto_eduardo_andres.viewData.perfilUsuarioData.PerfilUsuarioButtonTextsData
import com.example.proyecto_eduardo_andres.myComponents.componentePerfilUsuario.PerfilUsuarioButtons
import com.example.proyecto_eduardo_andres.viewData.perfilUsuarioData.PerfilUsuarioData
import com.example.proyecto_eduardo_andres.myComponents.componenteToolbar.toolBar
import com.example.proyecto_eduardo_andres.remote.RetrofitClient
import com.example.proyecto_eduardo_andres.repository.alquilerSeriesRepository.AlquilerSeriesRepositoryInMemory
import com.example.proyecto_eduardo_andres.repository.perfilRepositorio.PerfilUsuarioRepositoryInMemory
import com.example.proyecto_eduardo_andres.viewmodel.PerfilSeriesViewModel
import com.example.proyecto_eduardo_andres.viewmodel.PerfilSeriesViewModelFactory

@Composable
fun PerfilSeriesScreen(
    userId: String,
    viewModel: PerfilSeriesViewModel = viewModel(factory = PerfilSeriesViewModelFactory(
        userId,
        repository = PerfilUsuarioRepositoryInMemory(
            apiService = RetrofitClient.usuarioApiService
        ),
        alquilerRepository = AlquilerSeriesRepositoryInMemory()

    )),
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    // Llamamos a cargar los datos del usuario cuando se inicia el Composable
    LaunchedEffect(userId) {
        viewModel.cargarUsuario(userId)
    }

    // Recargar series alquiladas cuando se vuelve a la pantalla
    LaunchedEffect(Unit) {
        viewModel.recargarSeriesAlquiladas(userId)
    }

    val uiState by viewModel.uiState.collectAsState()

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
                    perfilUsarioData = PerfilUsuarioData(
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
                    text = stringResource(R.string.series_alquiladas),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Lista de series alquiladas
                if (uiState.seriesAlquiladas.isEmpty()) {
                    Text(
                        text = "No hay series alquiladas",
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
                        items(uiState.seriesAlquiladas) { serie ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Color.LightGray.copy(alpha = 0.3f),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (serie.imagen != null) {
                                    Image(
                                        painter = painterResource(id = serie.imagen),
                                        contentDescription = stringResource(serie.nombre),
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                                Column {
                                    Text(
                                        text = stringResource(serie.nombre),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = stringResource(serie.categoria),
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
                    perfilUsuarioButtonTextsData = PerfilUsuarioButtonTextsData(
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

        // --- AlertDialog de confirmación ---
        if (uiState.showConfirmacionDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.cerrarConfirmacionDialog() },
                title = {
                    Text(
                        text = "Cambios guardados",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                text = {
                    Text(
                        text = "Los datos del usuario se han modificado correctamente.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                confirmButton = {
                    TextButton(onClick = { viewModel.cerrarConfirmacionDialog() }) {
                        Text(stringResource(R.string.aceptar))
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilSeriesScreenPreview() {
    val repository = PerfilUsuarioRepositoryInMemory(
        apiService = RetrofitClient.usuarioApiService
    )
    val viewModel = PerfilSeriesViewModel(repository)
    // Para preview cargamos un usuario de prueba
    LaunchedEffect(Unit) {
        viewModel.cargarUsuario("1")
    }

    MaterialTheme {
        PerfilSeriesScreen(
            userId = "1",
            viewModel = viewModel,
            onBackClick = {},
            onHomeClick = {},
            onCameraClick = {},
            onProfileClick = {},
            onLogoutClick = {}
        )
    }
}

