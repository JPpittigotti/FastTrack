package com.example.fasttrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fasttrack.model.SolicitudServico

// Muestra la informacion completa de una sola solicitud
@Composable
fun PantallaDetalle(solicitud: SolicitudServico, alVolver: () -> Unit, alCambiarEstado: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Detalle de Solicitud", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(24.dp))

        // Tarjeta con todos los datos detallados
        Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Cliente: ${solicitud.cliente}", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Tipo: ${solicitud.tipoServicio}")
                Text(text = "Descripción: ${solicitud.descripcion}")
                Text(text = "Prioridad: ${solicitud.prioridad}")
                Text(text = "Técnico: ${solicitud.tecnicoAsignado}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Estado actual: ${solicitud.estado}", color = MaterialTheme.colorScheme.secondary, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Cambiar estado:")
        Spacer(modifier = Modifier.height(8.dp))

        // Botones para actualizar el progreso del servicio
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { alCambiarEstado("En Proceso") }, modifier = Modifier.weight(1f)) {
                Text(text = "En Proceso")
            }
            Button(onClick = { alCambiarEstado("Terminado") }, modifier = Modifier.weight(1f)) {
                Text(text = "Terminado")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(onClick = alVolver, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Volver a la lista")
        }
    }
}