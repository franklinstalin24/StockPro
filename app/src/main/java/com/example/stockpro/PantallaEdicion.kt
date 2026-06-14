package com.example.stockpro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaEdicion(navController: NavController, id: Int, viewModel: StockViewModel) {
    // Obtener el producto por su ID y si no existe, regresar a la pantalla anterior
    val producto = viewModel.obtenerProducto(id) ?: return

    // Diseño de la pantalla de edición
    // Usar un Column para organizar los elementos verticalmente
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = producto.nombre,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Descripcion: ${producto.descripcion}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Stock actual", style = MaterialTheme.typography.titleMedium)
        Text(
            text = "${producto.stockActual}",
            style = MaterialTheme.typography.displayLarge // Número grande para mostrar el stock actual
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Fila para los botones de aumentar/disminuir stock
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { viewModel.actualizarStock(id, producto.stockActual - 1) },
                enabled = producto.stockActual > 0) {
                Text("-", style = MaterialTheme.typography.headlineMedium)
            }

            Button(onClick = { viewModel.actualizarStock(id, producto.stockActual + 1) }) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Botón para guardar cambios y volver a la pantalla anterior

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar y volver")
        }
    }
}