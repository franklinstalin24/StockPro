package com.example.stockpro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.compareTo

@Composable
fun PantallaCatalogo(navController: NavController, nombre: String, viewModel: StockViewModel) {
    var mostrarcritico by remember { mutableStateOf(false) } // Estado para controlar si se muestran solo productos críticos o todos

    // Usar Scaffold para el diseño con un botón flotante
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("pantalla4") }) {
                Text("Reporte") // Botón para navegar a la pantalla de reporte
            }
        }
    ) { padding ->
        // Usar un padding para que no toque los bordes de la pantalla
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {

            Text(text = "Operario: $nombre", style = MaterialTheme.typography.titleMedium) // Mostrar nombre del operario

            Spacer(modifier = Modifier.height(16.dp))

            // Fila de botones con espaciado entre ellos
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { mostrarcritico = false }) { Text("Ver todo") }
                Button(onClick = { mostrarcritico = true }) { Text("Stock critico") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Filtrar la lista de productos segun el estado de mostrar solo critico
            val listafiltrada = if (mostrarcritico) {
                viewModel.productos.filter { it.stockActual < 5 }
            } else {
                viewModel.productos
            }

            LazyColumn(

                verticalArrangement = Arrangement.spacedBy(8.dp) // Esto añade espacio entre las tarjetas de la lista
            ) {
                items(listafiltrada) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("pantalla3/${producto.id}") }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Producto: ${producto.nombre}", fontWeight = FontWeight.Bold)
                            Text("Precio: $${producto.precio}")
                            Text(
                                text = "Stock: ${producto.stockActual}",
                                color = if (producto.stockActual < 5) Color.Red else Color.Black,
                                fontWeight = if (producto.stockActual < 5) FontWeight.Bold else FontWeight.Normal // Resalta en rojo y negrita si el stock es menor a 5, de lo contrario lo muestra normal
                            )
                        }
                    }
                }
            }
        }
    }
}