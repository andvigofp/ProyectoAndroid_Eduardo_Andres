package com.example.proyecto_eduardo_andres.vista.componente.componenteCamara

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun SolicitarPermisoCamara(onGranted: () -> Unit) {
    val context = LocalContext.current
    val permiso = Manifest.permission.CAMERA

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) onGranted()
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, permiso)
            == PackageManager.PERMISSION_GRANTED
        ) {
            onGranted()
        } else {
            launcher.launch(permiso)
        }
    }
}