package com.example.delivery.states

class FormProductUiState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val onSaveClick: () -> Unit = {},
    val onValueUrlChange: (text: String) -> Unit = {},
    val onValueNameChange: (text: String) -> Unit = {},
    val onValuePriceChange: (text: String) -> Unit = {},
    val onValueDescriptionChange: (text: String) -> Unit = {}
    ) {}