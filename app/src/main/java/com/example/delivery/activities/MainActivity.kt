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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import com.example.delivery.dao.ProductDao
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
                Home(products)
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