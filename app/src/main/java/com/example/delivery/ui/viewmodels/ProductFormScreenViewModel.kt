package com.example.delivery.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.delivery.dao.ProductDao
import com.example.delivery.model.Product
import com.example.delivery.states.FormProductUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<FormProductUiState> =
        MutableStateFlow(FormProductUiState())

    private val dao = ProductDao();

    val uiState get() = _uiState.asStateFlow()


    val formatter = DecimalFormat("#.##")

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onValueUrlChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                },
                onValueDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        description = it
                    )
                },

                onValuePriceChange = {
                    val price = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isBlank()) {
                            it;
                        } else {
                            null
                        }
                    }

                    price?.let {
                        _uiState.value = _uiState.value.copy(
                            price = price
                        )
                    }
                },

                onValueNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                }
            )
        }

    }


    fun save() {
        _uiState.value.run {
            val convertedPrice = try {
                BigDecimal(this.price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }

            val product = Product(
                name = this.name,
                image = this.url,
                price = convertedPrice,
                description = this.description
            )

            dao.save(product)
        }
    }
}