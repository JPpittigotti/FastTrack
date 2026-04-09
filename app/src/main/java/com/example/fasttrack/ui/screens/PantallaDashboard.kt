package com.example.fasttrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fasttrack.model.SolicitudServico

// Muestra las estadisticas y el conteo de como van los trabajos
@Composable
fun PantallaDashboard(lista: List<SolicitudServico>, alVolver: () -> Unit) {
    // Cuenta y clasifica los datos de la lista
    val total = lista.size
    val pendientes = lista.count { it.estado == "Pendiente" }
    val enProceso = lista.count { it.estado == "En Proceso" }
    val terminados = lista.count { it.estado == "Terminado" }
    val altaPrioridad = lista.count { it.prioridad == "Alta" }

    // Agregamos verticalScroll para que la pantalla se pueda deslizar y el boton de abajo siempre sea accesible
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        Text(text = "Resumen del Sistema", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(24.dp))

        // Muestra los resultados calculados
        Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Total de solicitudes: $total", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Pendientes: $pendientes")
                Text(text = "En proceso: $enProceso")
                Text(text = "Terminados: $terminados")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Urgentes (Alta prioridad): $altaPrioridad", color = MaterialTheme.colorScheme.error)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = alVolver, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Volver al Menú")
        }
    }
}