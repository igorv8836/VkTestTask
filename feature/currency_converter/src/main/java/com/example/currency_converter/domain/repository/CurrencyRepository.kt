package com.example.currency_converter.domain.repository

import com.example.currency_converter.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun getCurrencies(): Flow<List<Currency>>
    suspend fun convertCurrency(from: String, to: String, amount: Double): Flow<Double>
}