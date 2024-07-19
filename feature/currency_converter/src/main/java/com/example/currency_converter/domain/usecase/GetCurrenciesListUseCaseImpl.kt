package com.example.currency_converter.domain.usecase

import com.example.currency_converter.domain.model.Currency
import com.example.currency_converter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

internal class GetCurrenciesListUseCaseImpl(
    private val currencyRepository: CurrencyRepository
): GetCurrenciesListUseCase {
    override suspend fun getCurrencies(): Flow<List<Currency>> {
        return currencyRepository.getCurrencies()
    }
}