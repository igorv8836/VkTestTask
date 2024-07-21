package com.example.currency_converter.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ConvertCurrencyUseCase {
    fun convertCurrency(fromCharCode: String, toCharCode: String, amount: String): Flow<Double>
}