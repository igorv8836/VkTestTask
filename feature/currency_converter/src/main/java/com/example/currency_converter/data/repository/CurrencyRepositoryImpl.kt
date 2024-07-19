package com.example.currency_converter.data.repository

import com.example.currency_converter.domain.model.Currency
import com.example.currency_converter.domain.model.fromCurrencyResponse
import com.example.currency_converter.domain.model.fromCurrencyTable
import com.example.currency_converter.domain.model.toTable
import com.example.currency_converter.domain.repository.CurrencyRepository
import com.example.database.dao.CurrencyDao
import com.example.network.api.CurrencyApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao,
    private val currencyApi: CurrencyApiService
) : CurrencyRepository {
    override suspend fun getCurrencies(): Flow<List<Currency>> {
        return flow {
            val dbFlow = currencyDao.getAllCurrencies().map { data ->
                data.map(::fromCurrencyTable)
            }

            dbFlow.firstOrNull()?.let {
                emit(it)
            }

            val newData = currencyApi.getCurrency()
            val currencies =
                newData.valutes.map { fromCurrencyResponse(it.value, newData.timestamp).toTable() }
            currencyDao.insertCurrencies(currencies)
            emitAll(dbFlow)
        }
    }

    override suspend fun convertCurrency(fromCharCode: String, toCharCode: String, amount: Double): Flow<Double> {
        return flow {
            val fromCurrency = currencyDao.getCurrency(fromCharCode)
            val toCurrency = currencyDao.getCurrency(toCharCode)
            val result = amount * fromCurrency.value * fromCurrency.nominal / (toCurrency.value * toCurrency.nominal)
            emit(result)
        }
    }
}