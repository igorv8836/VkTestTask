package com.example.currency_converter.domain.usecase

import com.example.currency_converter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

internal class ConvertCurrencyUseCaseImpl(
    private val currencyRepository: CurrencyRepository
): ConvertCurrencyUseCase {
    override suspend fun convertCurrency(fromCharCode: String, toCharCode: String, amount: Double): Flow<Double> {
        return currencyRepository.convertCurrency(fromCharCode, toCharCode, amount)
    }

}