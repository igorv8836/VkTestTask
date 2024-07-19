package com.example.currency_converter.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ConvertCurrencyUseCase {
    suspend fun convertCurrency(fromCharCode: String, toCharCode: String, amount: Double): Flow<Double>
}