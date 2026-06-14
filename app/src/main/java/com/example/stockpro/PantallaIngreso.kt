package com.example.stockpro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaIngreso(navController: NavController) {
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Centrar verticalmente los elementos
    ) {
        Text("Bienvenido a StockPro", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp)) // Agrega un espacio entre el título y el campo de texto

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del operario") }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Agrega un espacio entre el campo de texto y el botón

        Button(
            onClick = { navController.navigate("pantalla2/${nombre.trim()}") },
            enabled = nombre.trim().length >= 3
        ) {
            Text("Ingresar al Sistema")
        }
    }
}