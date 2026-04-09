package com.example.fasttrack.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fasttrack.R
import com.example.fasttrack.viewmodel.ServicioViewModel

// Este es el control de trafico principal de la app
// Usamos un andamio para fijar el logo arriba y los derechos abajo
@Composable
fun PantallaPrincipal(vm: ServicioViewModel) {
    // rememberSaveable evita que se reinicie la navegacion al girar la pantalla
    var pantallaActual by rememberSaveable { mutableStateOf("splash") }
    // tambien guardamos el id para no perder la seleccion al rotar el celular
    var idSeleccionado by rememberSaveable { mutableStateOf(-1) }

    // Intercepta el boton de atras del celular
    // Si no estamos en el splash ni en el menu, nos devuelve al menu en vez de salir de la app
    BackHandler(enabled = pantallaActual != "splash" && pantallaActual != "menu") {
        pantallaActual = "menu"
    }

    Scaffold(
        // Barra superior con el logo centrado y en grande
        topBar = {
            if (pantallaActual != "splash") {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_app),
                        contentDescription = null,
                        modifier = Modifier.size(90.dp) // Tamaño lo suficientemente grande para que resalte
                    )
                }
            }
        },
        // Barra inferior que ahora solo tiene el texto de los derechos de autor
        bottomBar = {
            if (pantallaActual != "splash") {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "© 2026 NovaApps Studio - Todos los derechos reservados",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    ) { paddingValues ->
        // El contenido de las pantallas se dibuja aqui, respetando el espacio de arriba y abajo
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