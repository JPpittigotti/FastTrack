package com.example.fasttrack.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fasttrack.R
import kotlinx.coroutines.delay

// Pantalla de carga que se ve al abrir la aplicacion
@Composable
fun SplashScreen(alTerminar: () -> Unit) {
    // Esto corre por debajo y espera 2 segundos antes de avanzar
    LaunchedEffect(Unit) {
        delay(2000)
        alTerminar()
    }

    // Centra todo en el medio de la pantalla
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "FastTrack", fontSize = 32.sp, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Gestión inteligente de servicios", fontSize = 16.sp)
        }
    }
}