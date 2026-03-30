package com.example.fasttrack.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fasttrack.R
import com.example.fasttrack.viewmodel.ServicioViewModel

// Este es el control de trafico principal de la app
// Usamos Scaffold para dejar una barra fija abajo con el logo y los derechos
@Composable
fun PantallaPrincipal(vm: ServicioViewModel) {
    // Guarda el nombre de la pantalla actual
    var pantallaActual by remember { mutableStateOf("splash") }
    // Guarda el numero de la solicitud que queremos ver a detalle
    var idSeleccionado by remember { mutableStateOf(-1) }

    Scaffold(
        // Esta es la barra que se quedara pegada abajo en todas las pantallas
        bottomBar = {
            // Ocultamos la barra solo cuando estamos en la pantalla de carga inicial
            if (pantallaActual != "splash") {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Texto de los derechos de autor centrado
                    Text(
                        text = "© 2026 FastTrack Studio - Todos los derechos reservados",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    // Logo de la app acomodado a la derecha para que no estorbe
                    Image(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterEnd).size(32.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        // El contenido de las pantallas se dibuja aqui, respetando el espacio de la barra
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
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
    }
}