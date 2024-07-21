package com.example.currency_converter.domain.repository

import com.example.currency_converter.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencies(): Flow<List<Currency>>
    fun convertCurrency(fromCharCode: String, toCharCode: String, amount: String): Flow<Double>
}