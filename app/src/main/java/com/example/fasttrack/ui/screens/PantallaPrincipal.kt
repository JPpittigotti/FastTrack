package com.example.fasttrack.ui.screens

import androidx.compose.runtime.*
import com.example.fasttrack.viewmodel.ServicioViewModel

// Esta pantalla funciona como un control de trafico
// Decide que pantalla mostrar segun lo que el usuario haga
@Composable
fun PantallaPrincipal(vm: ServicioViewModel) {
    // Guarda el nombre de la pantalla actual
    var pantallaActual by remember { mutableStateOf("splash") }
    // Guarda el numero de la solicitud que queremos ver a detalle
    var idSeleccionado by remember { mutableStateOf(-1) }

    // Cambia la vista dependiendo del valor de pantallaActual
    when (pantallaActual) {
        "splash" -> SplashScreen { pantallaActual = "menu" }
        "menu" -> PantallaMenu(
            alIrRegistro = { pantallaActual = "formulario" },
            alIrLista = { pantallaActual = "lista" },
            alIrDashboard = { pantallaActual = "dashboard" }
        )
        "formulario" -> PantallaFormulario(
            alGuardar = { c, t, d, p, tec ->
                // Intenta guardar y si sale bien cambia a la lista
                val ok = vm.agregarRegistro(c, t, d, p, tec)
                if (ok) pantallaActual = "lista"
                ok
            },
            alCancelar = { pantallaActual = "menu" }
        )
        "lista" -> PantallaLista(
            lista = vm.listaSolicitudes,
            alVolver = { pantallaActual = "menu" },
            alVerDetalle = { id ->
                idSeleccionado = id
                pantallaActual = "detalle"
            }
        )
        "detalle" -> {
            // Busca la solicitud seleccionada para mostrarla
            val solicitud = vm.listaSolicitudes.find { it.id == idSeleccionado }
            if (solicitud != null) {
                PantallaDetalle(
                    solicitud = solicitud,
                    alVolver = { pantallaActual = "lista" },
                    alCambiarEstado = { nuevoEstado ->
                        vm.actualizarEstado(solicitud.id, nuevoEstado)
                    }
                )
            } else {
                pantallaActual = "lista"
            }
        }
        "dashboard" -> PantallaDashboard(
            lista = vm.listaSolicitudes,
            alVolver = { pantallaActual = "menu" }
        )
    }
}