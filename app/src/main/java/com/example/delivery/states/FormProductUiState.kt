package com.example.delivery.states

data class FormProductUiState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val onValueUrlChange: (text: String) -> Unit = {},
    val onValueNameChange: (text: String) -> Unit = {},
    val onValuePriceChange: (text: String) -> Unit = {},
    val onValueDescriptionChange: (text: String) -> Unit = {}
    ) {}