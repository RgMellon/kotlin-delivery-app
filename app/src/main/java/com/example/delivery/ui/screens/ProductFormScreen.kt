package com.example.delivery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.delivery.R
import com.example.delivery.states.FormProductUiState
import com.example.delivery.ui.viewmodels.ProductFormScreenViewModel


@Composable
fun ProductFormScreen(
    viewModel: ProductFormScreenViewModel,
    onSaveClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    ProductFormScreen(state = state, onSaveClick = {
        viewModel.save()
        onSaveClick()
    })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(state: FormProductUiState, onSaveClick: () -> Unit = { }) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "Criando o produto", modifier = Modifier.fillMaxWidth(), fontSize = 28.sp)

        if (state.url.isNotBlank()) {
            AsyncImage(
                model = state.url, contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }

        Spacer(modifier = Modifier)

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.url,
            onValueChange = state.onValueUrlChange,
            label = {
                Text(text = "Url da imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name,
            onValueChange = state.onValueNameChange,
            label = { Text(text = "Nome") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.price,
            onValueChange = state.onValuePriceChange,
            label = {
                Text(text = "Preço")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp),
            value = state.description,
            onValueChange = state.onValueDescriptionChange,
            label = {
                Text(text = "Descrição")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Button(onClick = onSaveClick, Modifier.fillMaxWidth()) {
            Text(text = "Salvar")
        }

        Spacer(modifier = Modifier)
    }
}


@Preview
@Composable
fun ProductFormScreenPrev() {
    ProductFormScreen(state = FormProductUiState())
}