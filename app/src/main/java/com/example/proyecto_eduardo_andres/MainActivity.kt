package com.example.proyecto_eduardo_andres

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.Proyecto_Eduardo_AndresTheme
import com.example.proyecto_eduardo_andres.vista.pagina.AppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_Eduardo_AndresTheme {
                Scaffold { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        AppNavigation()

                        /**LogingScreen(
                        userImageUrl = null,
                        onAccederClick = {},
                        onCrearUsuarioClick = {},
                        onRecuperarPasswordClick = {}
                        )
                         **/

                        //CrearUsuarioScreen()
                        //RecuperarPasswordScreen()
                        //PerfilUsuarioScreen()
                        //RecuperarPasswordScreen()
                        //VideoClubOnlinePeliculasScreen()
                        //VideoClubOnlineSeriesScreen(navController)
                        //VideoClubSearchPeliculasScreen(navController)
                        //VideoClubSearchSeriesScreen(navController)
                        //AlquilerDevolverSeriesScreen()
                        //AlquilarDevolverPeliculasScreen()

                        /**QRScreen(
                        qrData = QRDto("https://ejemplo.com"),
                        onBackClick = {},
                        onHomeClick = {},
                        onCameraClick = {},
                        onProfileClick = {},
                        onLogoutClick = {}
                        )**/

                        //CamaraScreen()
                    }
                }

            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Proyecto_Eduardo_AndresTheme {
            /**LogingScreen(
            userImageUrl = null,
            onAccederClick = {},
            onCrearUsuarioClick = {},
            onRecuperarPasswordClick = {}
            )
             **/

            //CrearUsuarioScreen()
            //RecuperarPasswordScreen()
            //PerfilUsuarioScreen()
            //RecuperarPasswordScreen()
            //VideoClubOnlinePeliculasScreen(navController)
            //VideoClubOnlineSeriesScreen()
            //VideoClubSearchPeliculasScreen()
            //AlquilerDevolverSeriesScreen()
            //AlquilarDevolverPeliculasScreen()

            /**QRScreen(
                qrData = QRDto("https://ejemplo.com"),
                onBackClick = {},
                onHomeClick = {},
                onCameraClick = {},
                onProfileClick = {},
                onLogoutClick = {}
            )**/

            //CamaraScreen()
        }
    }
}