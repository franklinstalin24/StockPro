package com.example.stockpro

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.example.stockpro.Producto


class StockViewModel : ViewModel() {
    val productos = mutableStateListOf(
        Producto(1, "Martillo", "Mango de madera", 7.0, 5),
        Producto(2, "Brocha", "Para pintura en hogar", 2.0, 3),
        Producto(3, "Metro", "Standard", 4.0, 0),
        Producto(4, "Serrucho", "Para madera", 6.0, 8),
        Producto(5, "Destornillador", "Estrella", 5.0, 2),
        Producto(6, "Silicona", "Sellador multiusos", 4.0, 12)
    )

    // Función para obtener un producto por su ID
    fun obtenerProducto(id: Int) = productos.find { it.id == id }

    // Función para actualizar el stock de un producto
    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = productos.indexOfFirst { it.id == id }
        if (index != -1) {
            productos[index] = productos[index].copy(stockActual = nuevaCantidad)
        }
    }

    // Función para calcular el valor total del inventario sumando el precio por el stock de cada producto
    fun calcularValorTotalInventario() = productos.sumOf { it.precio * it.stockActual }

    // Función para obtener una lista de productos que están en riesgo (stock menor a 5)
    fun obtenerProductosEnRiesgo() = productos.filter { it.stockActual < 5 }

    // Función para contar cuántos productos tienen stock actual igual a cero
    fun totalStockCero() = productos.count { it.stockActual == 0 }

}