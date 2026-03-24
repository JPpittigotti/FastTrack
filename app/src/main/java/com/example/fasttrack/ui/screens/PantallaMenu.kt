package com.example.fasttrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Menu principal con botones para navegar a las distintas opciones
@Composable
fun PantallaMenu(alIrRegistro: () -> Unit, alIrLista: () -> Unit, alIrDashboard: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Menú Principal", fontSize = 28.sp, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = alIrRegistro, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text(text = "Registrar Solicitud")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = alIrLista, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text(text = "Ver Solicitudes")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = alIrDashboard, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text(text = "Resumen y Estadísticas")
        }
    }
}