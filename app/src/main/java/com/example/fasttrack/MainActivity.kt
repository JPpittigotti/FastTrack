package com.example.fasttrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fasttrack.ui.screens.PantallaPrincipal
import com.example.fasttrack.viewmodel.ServicioViewModel

// Esta es la base de la aplicacion, lo primero que arranca
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Creamos el ViewModel que manejara toda la informacion
            val vm: ServicioViewModel = viewModel()
            // Llamamos a la pantalla que controla la navegacion
            PantallaPrincipal(vm)
        }
    }
}