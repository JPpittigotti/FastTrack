package com.example.fasttrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Pantalla donde el usuario llena los datos para un nuevo servicio
@Composable
fun PantallaFormulario(
    alGuardar: (String, String, String, String, String) -> Boolean,
    alCancelar: () -> Unit
) {
    // Usamos rememberSaveable para que el texto escrito no se borre si el usuario gira el celular
    var cliente by rememberSaveable { mutableStateOf("") }
    var tipo by rememberSaveable { mutableStateOf("") }
    var desc by rememberSaveable { mutableStateOf("") }
    var prioridad by rememberSaveable { mutableStateOf("Baja") }
    var tecnico by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(false) }

    // Agregamos verticalScroll para que el formulario se pueda deslizar hacia abajo
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
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