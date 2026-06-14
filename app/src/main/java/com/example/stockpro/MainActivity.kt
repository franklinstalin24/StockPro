package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockpro.ui.theme.StockProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockProTheme {
                // Función que contiene toda la lógica de navegación y pantallas
                StockProApp()
            }
        }
    }
}

@Composable
fun StockProApp(viewModel: StockViewModel = viewModel()) {
    val navController = rememberNavController()

    // El navhost controla el paso entre las 4 pantallas
    NavHost(navController = navController, startDestination = "pantalla1") {

        composable("pantalla1") {
            PantallaIngreso(navController) // Pantalla de ingreso con el TextField para el nombre del operario
        }

        composable("pantalla2") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            PantallaCatalogo(navController, nombre, viewModel) // Pantalla de catálogo que muestra el nombre del operario y la lista de productos
        }

        composable("pantalla3") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            PantallaEdicion(navController, id, viewModel) // Pantalla de edición que muestra el detalle del producto seleccionado y permite actualizar el stock
        }

        composable("pantalla4") {
            PantallaReporte(navController, viewModel) // Pantalla de reporte que muestra un resumen del stock actual, productos críticos y productos sin stock
        }
    }
}