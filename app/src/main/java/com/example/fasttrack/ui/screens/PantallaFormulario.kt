package com.example.fasttrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Pantalla donde el usuario llena los datos para un nuevo servicio
@Composable
fun PantallaFormulario(
    alGuardar: (String, String, String, String, String) -> Boolean,
    alCancelar: () -> Unit
) {
    // Estas variables guardan temporalmente lo que el usuario escribe
    var cliente by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("Baja") }
    var tecnico by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Nueva Solicitud", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))

        // Cajas de texto para ingresar la informacion
        OutlinedTextField(value = cliente, onValueChange = { cliente = it; error = false }, label = { Text("Cliente") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = tipo, onValueChange = { tipo = it; error = false }, label = { Text("Tipo de servicio") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = desc, onValueChange = { desc = it; error = false }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = tecnico, onValueChange = { tecnico = it; error = false }, label = { Text("Técnico asignado") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Prioridad:")
        // Botones redondos para elegir la prioridad del servicio
        Row {
            RadioButton(selected = prioridad == "Baja", onClick = { prioridad = "Baja" })
            Text(text = "Baja", modifier = Modifier.padding(top = 12.dp, end = 16.dp))
            RadioButton(selected = prioridad == "Media", onClick = { prioridad = "Media" })
            Text(text = "Media", modifier = Modifier.padding(top = 12.dp, end = 16.dp))
            RadioButton(selected = prioridad == "Alta", onClick = { prioridad = "Alta" })
            Text(text = "Alta", modifier = Modifier.padding(top = 12.dp))
        }

        // Muestra un mensaje rojo si falta algo al intentar guardar
        if (error) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Completa todos los campos", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = alCancelar, modifier = Modifier.weight(1f)) {
                Text(text = "Cancelar")
            }
            Button(onClick = {
                val ok = alGuardar(cliente, tipo, desc, prioridad, tecnico)
                if (!ok) error = true
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Guardar")
            }
        }
    }
}