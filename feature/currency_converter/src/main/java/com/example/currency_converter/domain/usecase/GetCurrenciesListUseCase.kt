package com.example.currency_converter.domain.usecase

import com.example.currency_converter.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface GetCurrenciesListUseCase {
    suspend fun getCurrencies(): Flow<List<Currency>>
}