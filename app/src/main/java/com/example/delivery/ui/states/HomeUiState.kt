package com.example.delivery.ui.states

import com.example.delivery.model.Product

data class HomeUiState(
    val searchText: String = "",
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSection(): Boolean {
        return searchText.isBlank()
    }
}
