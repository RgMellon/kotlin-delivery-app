package com.example.delivery.ui.screens

import ProductItemSection
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import com.example.delivery.model.Product
import com.example.delivery.ui.components.CardProductItem
import com.example.delivery.ui.components.SearchText
import com.example.delivery.ui.theme.DeliveryTheme


class HomeUiState(
    val searchText: String = "",
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSection(): Boolean {
        return searchText.isBlank()
    }
}


@Composable
fun Home(products: List<Product>) {
    val sections = mapOf(
        "Todos os produtos" to products,
        "Doces" to sampleCandies
    )

    var text  by remember {
        mutableStateOf("")
    }

    fun containInNameOrDescription() = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(text, ignoreCase = true) ?: false
    }

    val searchedProducts =  remember(text, products) {
        if (text.isNotBlank()) {
            sampleProducts.filter(containInNameOrDescription()) + products.filter(containInNameOrDescription())
        } else {
            emptyList()
        }
    }

    val state =  remember(products, text) {
        HomeUiState(
            sections = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }

    Home(state = state)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(state: HomeUiState = HomeUiState()) {

    val sections = state.sections
    val searchedProducts =  state.searchedProducts

    Column {
        SearchText(
            searchText = state.searchText,
            onSearchChange = state.onSearchChange
        )

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSection()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value

                    item {
                        ProductItemSection(
                            title = title,
                            products = products,
                            modifier = Modifier
                        )
                    }
                }
            } else {
                items(searchedProducts) { product ->
                    CardProductItem(
                        product = product,
                        Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    DeliveryTheme {
        Surface {
            Home(
                state = HomeUiState(
                    "a",
                    sections = sampleSections
                )
            )
        }
    }
}
