package com.example.currency_converter.domain.model

import com.example.database.entity.CurrencyTable
import com.example.network.dto.Valute

data class Currency(
    val charCode: String,
    val name: String,
    val nominal: Int,
    val value: Double,
    val timestamp: String
)

internal fun fromCurrencyResponse(valute: Valute, time: String): Currency {
    return Currency(
        valute.charCode,
        valute.name,
        valute.nominal,
        valute.value,
        time
    )
}

internal fun fromCurrencyTable(data: CurrencyTable): Currency {
    return Currency(
        data.charCode,
        data.name,
        data.nominal,
        data.value,
        data.timestamp
    )
}

internal fun Currency.toTable(): CurrencyTable {
    return CurrencyTable(
        charCode,
        name,
        nominal,
        value,
        timestamp
    )
}