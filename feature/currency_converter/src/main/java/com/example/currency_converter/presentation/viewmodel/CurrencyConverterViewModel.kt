package com.example.currency_converter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Result
import com.example.common.asResult
import com.example.currency_converter.domain.usecase.ConvertCurrencyUseCase
import com.example.currency_converter.domain.usecase.GetCurrenciesListUseCase
import com.example.currency_converter.presentation.screen.ConverterScreenEvent
import com.example.currency_converter.presentation.screen.ConverterScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class CurrencyConverterViewModel(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val getCurrenciesListUseCase: GetCurrenciesListUseCase
) : ViewModel() {

    private val convertedResult = MutableStateFlow(0.0)
    private val suggestions = MutableStateFlow(emptyList<String>())
    private val currencies = getCurrenciesListUseCase.getCurrencies()

    val uiState = combine(
        currencies.map { it.map { it1 -> it1.name } },
        convertedResult,
        suggestions,
        ConverterScreenState::Content
    ).asResult().map { data ->
        when (data) {
            is Result.Loading -> ConverterScreenState.Loading
            is Result.Error -> ConverterScreenState.Error(data.exception.message ?: "Error")
            is Result.Success -> data.data
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ConverterScreenState.Loading
    )

    fun invoke(event: ConverterScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is ConverterScreenEvent.Convert -> convertCurrency(
                    event.fromCharCode,
                    event.toCharCode,
                    event.amount
                )

                is ConverterScreenEvent.RefreshCurrencies -> getCurrenciesListUseCase.getCurrencies()
                is ConverterScreenEvent.GetFilteredCurrencies -> {
                    currencies.firstOrNull()?.let {
                        val answer = it.filter { it1 ->
                            it1.name.lowercase()
                                .contains(event.filter.lowercase()) || it1.charCode.lowercase()
                                .contains(event.filter.lowercase())
                        }.map { it1 -> it1.name }
                        suggestions.emit(answer)
                    }
                }
            }
        }

    }

    private fun convertCurrency(fromName: String, toName: String, amount: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val fromChar = currencies.firstOrNull()?.firstOrNull { it.name == fromName }?.charCode
            val toChar = currencies.firstOrNull()?.firstOrNull { it.name == toName }?.charCode
            if (fromChar == null || toChar == null){
                return@launch
            }
            val result = convertCurrencyUseCase.convertCurrency(fromChar, toChar, amount)
            convertedResult.emitAll(result)
        }
    }
}