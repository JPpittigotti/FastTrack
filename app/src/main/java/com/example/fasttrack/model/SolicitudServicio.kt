package com.example.fasttrack.model

// Este es el molde para crear cada solicitud de servicio
// Define que datos vamos a guardar de cada trabajo
data class SolicitudServico(
    val id: Int,
    val cliente: String,
    val tipoServicio: String,
    val descripcion: String,
    val prioridad: String,
    val tecnicoAsignado: String,
    var estado: String = "Pendiente" // Todas inician como Pendiente por defecto
)