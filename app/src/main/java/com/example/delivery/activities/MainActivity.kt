package com.example.delivery.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import com.example.delivery.dao.ProductDao
import com.example.delivery.model.Product
import com.example.delivery.ui.screens.Home
import com.example.delivery.ui.screens.HomeUiState
import com.example.delivery.ui.theme.DeliveryTheme

class MainActivity : ComponentActivity() {
    private  val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, FormProductActivity::class.java))
            }) {

                val products =  dao.products()
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
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(onFabClick: () -> Unit = {}, content: @Composable () -> Unit = {}) {
    DeliveryTheme {
        Surface {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = onFabClick) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add button"
                        )
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                   content()
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App {
        val state = HomeUiState(sections = sampleSections, searchedProducts = sampleProducts)
        Home(state)
    }
}