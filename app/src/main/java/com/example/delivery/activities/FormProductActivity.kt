package com.example.delivery.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.delivery.ui.screens.ProductFormScreen
import com.example.delivery.ui.theme.DeliveryTheme

class FormProductActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun goBack() {
            finish()
        }

        setContent {
            DeliveryTheme {
                Surface {
                    ProductFormScreen(goBack = {
                        goBack()
                    })
                }
            }
        }
    }
}

