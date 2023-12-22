package com.example.delivery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import com.example.delivery.dao.ProductDao
import com.example.delivery.model.Product
import com.example.delivery.ui.states.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val dao = ProductDao()
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())

    val uiState get () = _uiState.asStateFlow()


    init{
    // para obtermos o valor atual - traz o  estado atual na expressao lambda
        _uiState.update { currentState -> currentState.copy(
            onSearchChange = {
                _uiState.value = _uiState.value.copy(
                    searchText =  it,
                    searchedProducts = searchedProducts(it)
                )
            }
        ) }


        viewModelScope.launch {
            dao.products().collect{ products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos produtos" to products,
                        "Promoções" to sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    searchedProducts = searchedProducts(_uiState.value.searchText)
                )
            }
        }
    }
    private fun containInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(_uiState.value.searchText, ignoreCase = true) ?: false
    }

    private fun searchedProducts(text: String): List<Product> {
        return if (text.isNotBlank()) {
            sampleProducts.filter(containInNameOrDescription(text)) + dao.products().value
                .filter(containInNameOrDescription(text))
        } else {
            emptyList()
        }
    }

}