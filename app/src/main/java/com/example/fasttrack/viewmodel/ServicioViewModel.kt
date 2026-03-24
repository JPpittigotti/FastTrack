package com.example.fasttrack.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.fasttrack.model.SolicitudServico

// El ViewModel guarda los datos para que no se borren si se gira la pantalla
class ServicioViewModel : ViewModel() {
    // Esta lista guarda todas las solicitudes y avisa a la pantalla cuando hay cambios
    var listaSolicitudes = mutableStateListOf<SolicitudServico>()

    // Sirve para darle un numero unico a cada solicitud nueva
    private var contadorId = 1

    // Revisa que los datos no esten vacios y crea una nueva solicitud
    fun agregarRegistro(cliente: String, tipo: String, desc: String, prioridad: String, tecnico: String): Boolean {
        if (cliente.isBlank() || tipo.isBlank() || desc.isBlank() || tecnico.isBlank()) {
            return false // Falla si falta algun dato
        }

        val nueva = SolicitudServico(
            id = contadorId++,
            cliente = cliente,
            tipoServicio = tipo,
            descripcion = desc,
            prioridad = prioridad,
            tecnicoAsignado = tecnico
        )

        listaSolicitudes.add(nueva)
        return true // Avisa que se guardo bien
    }

    // Busca una solicitud por su numero y le cambia el estado
    fun actualizarEstado(id: Int, nuevoEstado: String) {
        val index = listaSolicitudes.indexOfFirst { it.id == id }
        if (index != -1) {
            val actual = listaSolicitudes[index]
            listaSolicitudes[index] = actual.copy(estado = nuevoEstado)
        }
    }
}