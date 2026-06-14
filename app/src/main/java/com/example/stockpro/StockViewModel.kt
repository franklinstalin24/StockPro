package com.example.stockpro

import androidx.compose.runtime.mutableStateListOf

class StockViewModel {
    val productos = mutableStateListOf(
        Producto(1, "Martillo", "Mango de madera", 7.0, 5),
        Producto(2, "Brocha", "Para pintura en hogar", 2.0, 3),
        Producto(3, "Metro", "Standard", 4.0, 0),
        Producto(4, "Serrucho", "Para madera", 6.0, 8),
        Producto(5, "Destornillador", "Estrella", 5.0, 2),
        Producto(6, "Silicona", "Sellador multiusos", 4.0, 12)
    )

}