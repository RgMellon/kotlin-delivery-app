package com.example.delivery.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.delivery.ui.screens.ProductFormScreen
import com.example.delivery.ui.theme.DeliveryTheme
import com.example.delivery.ui.viewmodels.ProductFormScreenViewModel

class FormProductActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DeliveryTheme {
                Surface {
                    val viewModel: ProductFormScreenViewModel by viewModels()

                    ProductFormScreen(
                            viewModel,
                            onSaveClick = {
                                finish();
                            }
                    )
                }
            }
        }
    }
}

