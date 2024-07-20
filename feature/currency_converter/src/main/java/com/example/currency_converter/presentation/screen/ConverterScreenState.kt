package com.example.currency_converter.presentation.screen

import com.example.currency_converter.domain.model.Currency

internal sealed interface ConverterScreenState {
    data object Loading : ConverterScreenState
    data class Content(val data: ConverterScreenData) : ConverterScreenState
    data class Error(val message: String) : ConverterScreenState
}

internal sealed interface ConverterScreenEvent {
    data class Convert(val fromCharCode: String, val toCharCode: String, val amount: Double) : ConverterScreenEvent
    data object RefreshCurrencies : ConverterScreenEvent
}

internal data class ConverterScreenData(
    val currencies: List<Currency>,
    val convertedValue: Double?
)