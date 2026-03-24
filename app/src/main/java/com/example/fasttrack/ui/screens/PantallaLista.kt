package com.example.fasttrack.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fasttrack.model.SolicitudServico

// Pantalla que muestra todas las solicitudes que se han guardado
@Composable
fun PantallaLista(lista: List<SolicitudServico>, alVolver: () -> Unit, alVerDetalle: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Lista de Solicitudes", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
            Button(onClick = alVolver) {
                Text(text = "Menú")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Si la lista esta vacia avisa que no hay datos
        if (lista.isEmpty()) {
            Text(text = "No hay solicitudes registradas.")
        } else {
            // Crea una lista que se puede deslizar y no consume mucha memoria
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(lista) { item ->
                    // Tarjeta individual para cada solicitud que reacciona al toque
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { alVerDetalle(item.id) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = item.cliente, fontSize = 18.sp)
                            Text(text = "Servicio: ${item.tipoServicio}")
                            Text(text = "Prioridad: ${item.prioridad}")
                            Text(text = "Estado: ${item.estado}", color = MaterialTheme.colorScheme.secondary)
                        }
                    }
                }
            }
        }
    }
}