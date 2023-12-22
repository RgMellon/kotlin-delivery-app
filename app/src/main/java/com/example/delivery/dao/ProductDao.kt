package com.example.delivery.dao

import com.example.delivery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {
    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }


    //toList devolve uma copia da lista de produtos imutavel
    fun products() = products.asStateFlow()

    fun save(product:Product) {
        products.value = products.value + product
    }
}