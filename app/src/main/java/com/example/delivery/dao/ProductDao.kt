package com.example.delivery.dao

import androidx.compose.runtime.mutableStateListOf
import com.example.delivery.model.Product

class ProductDao {
    companion object {
        private val products = mutableStateListOf<Product>()
    }


    //toList devolve uma copia da lista de produtos imutavel
    fun products() = products.toList()

    fun save(product:Product) {
        products.add(product)
    }
}