package com.example.currency_converter.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currency_converter.R
import com.example.currency_converter.presentation.screen.ui_items.CurrencySumTextField
import com.example.currency_converter.presentation.screen.ui_items.CurrencyTextField
import com.example.currency_converter.presentation.screen.ui_items.TextFieldGroup
import com.example.currency_converter.presentation.viewmodel.CurrencyConverterViewModel
import com.example.ui_theme.app_theme.AppTheme

@Composable
internal fun CurrencyConverterScreen(
    viewModel: CurrencyConverterViewModel,
    snackbarHostState: SnackbarHostState
) {

    val state by viewModel.uiState.collectAsState()

    val fromCurrencyText = remember { mutableStateOf("Доллар США") }
    val toCurrencyText = remember { mutableStateOf("Российский рубль") }
    val fromCurrencyTextRes = remember { mutableStateOf("") }
    val toCurrencyTextRes = remember { mutableStateOf("") }
    val fromCurrencyExpanded = remember { mutableStateOf(false) }
    val toCurrencyExpanded = remember { mutableStateOf(false) }

    fun getConvertedData(){
        viewModel.invoke(
            ConverterScreenEvent.Convert(
                fromCurrencyText.value,
                toCurrencyText.value,
                fromCurrencyTextRes.value
            )
        )
    }


    when (state) {
        is ConverterScreenState.Loading -> {
            Box(
                modifier = Modifier
                    .zIndex(1f)
                    .fillMaxSize()
                    .alpha(0.5f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {}
                    .background(MaterialTheme.colorScheme.scrim)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is ConverterScreenState.Error -> {
            LaunchedEffect(key1 = (state as? ConverterScreenState.Error)?.message) {
                snackbarHostState.showSnackbar(
                    (state as? ConverterScreenState.Error)?.message ?: "Error"
                )
            }
        }

        is ConverterScreenState.Content -> {
            (state as? ConverterScreenState.Content)?.let {
                toCurrencyTextRes.value = it.convertedValue.toString()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldGroup(
            textField1 = {
                CurrencyTextField(
                    text = fromCurrencyText,
                    hint = "Исходная валюта",
                    trailingIconId = R.drawable.change,
                    expanded = fromCurrencyExpanded,
                    suggestions = (state as? ConverterScreenState.Content)?.suggestions ?: emptyList(),
                    suggestionIsSelected = {
                        fromCurrencyText.value = it
                        fromCurrencyExpanded.value = false
                        getConvertedData()
                    },
                    getFilteredCurrencies = {
                        viewModel.invoke(ConverterScreenEvent.GetFilteredCurrencies(it))
                    }
                ) {
                    val temp = fromCurrencyText.value
                    fromCurrencyText.value = toCurrencyText.value
                    toCurrencyText.value = temp

                    getConvertedData()
                }
            },
            textField2 = {
                CurrencyTextField(
                    text = toCurrencyText,
                    hint = "Целевая валюта",
                    trailingIconId = null,
                    expanded = toCurrencyExpanded,
                    suggestions = (state as? ConverterScreenState.Content)?.suggestions ?: emptyList(),
                    suggestionIsSelected = {
                        toCurrencyText.value = it
                        toCurrencyExpanded.value = false
                        getConvertedData()
                    },
                    getFilteredCurrencies = {
                        viewModel.invoke(ConverterScreenEvent.GetFilteredCurrencies(it))
                    }
                ) {}
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldGroup(
            textField1 = {
                CurrencySumTextField(
                    text = fromCurrencyTextRes,
                    hint = "Введите количество",
                    readOnly = false,
                ) {
                    getConvertedData()
                }
            },
            textField2 = {
                CurrencySumTextField(
                    text = toCurrencyTextRes,
                    hint = "Результат конвертирования",
                    readOnly = true
                ) {}
            }
        )
    }
}


@Composable
@Preview
internal fun CurrencyConverterScreenPreview() {
    AppTheme {
        CurrencyConverterScreen(viewModel(), SnackbarHostState())
    }
}