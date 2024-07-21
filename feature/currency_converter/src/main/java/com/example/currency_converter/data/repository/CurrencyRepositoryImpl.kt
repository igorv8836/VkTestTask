package com.example.currency_converter.data.repository

import com.example.currency_converter.domain.model.Currency
import com.example.currency_converter.domain.model.fromCurrencyResponse
import com.example.currency_converter.domain.model.fromCurrencyTable
import com.example.currency_converter.domain.model.toTable
import com.example.currency_converter.domain.repository.CurrencyRepository
import com.example.database.dao.CurrencyDao
import com.example.network.api.CurrencyApiService
import com.example.network.dto.Valute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class CurrencyRepositoryImpl(
    private val currencyDao: CurrencyDao,
    private val currencyApi: CurrencyApiService
) : CurrencyRepository {
    override fun getCurrencies(): Flow<List<Currency>> {
        return flow {
            try {
                val dbFlow = currencyDao.getAllCurrencies().map { data ->
                    data.map(::fromCurrencyTable)
                }

                dbFlow.firstOrNull()?.let {
                    emit(it)
                }

                val newData = currencyApi.getCurrency()
                val valutes = HashMap(newData.valutes)
                valutes["RUB"] = Valute(
                    "",
                    "RUB",
                    "RUB",
                    1,
                    "Российский рубль",
                    1.0,
                    1.0
                )
                val newDataWithRUB = newData.copy(valutes = valutes)
                val currencies =
                    newDataWithRUB.valutes.map { fromCurrencyResponse(it.value, newDataWithRUB.timestamp).toTable() }
                currencyDao.insertCurrencies(currencies)
                emitAll(dbFlow)
            } catch (_: Exception){}
        }
    }

    override fun convertCurrency(fromCharCode: String, toCharCode: String, amount: String): Flow<Double> {
        return flow {
            try {
                val fromCurrency = currencyDao.getCurrency(fromCharCode)
                val toCurrency = currencyDao.getCurrency(toCharCode)
                val result = amount.toDouble() * fromCurrency.value / fromCurrency.nominal / (toCurrency.value / toCurrency.nominal)
                emit(result)
            } catch (_: Exception){
                emit(0.0)
            }
        }
    }
}