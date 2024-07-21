package com.example.currency_converter.presentation.screen

internal sealed interface ConverterScreenState {
    data object Loading : ConverterScreenState
    data class Content(
        val currencies: List<String>,
        val convertedValue: Double?,
        val suggestions: List<String>
    ) : ConverterScreenState

    data class Error(val message: String) : ConverterScreenState
}

internal sealed interface ConverterScreenEvent {
    data class Convert(val fromCharCode: String, val toCharCode: String, val amount: String) :
        ConverterScreenEvent

    data object RefreshCurrencies : ConverterScreenEvent
    data class GetFilteredCurrencies(val filter: String) : ConverterScreenEvent
}