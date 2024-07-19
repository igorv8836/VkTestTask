package com.example.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyResponse(
    @Json(name = "Date") val date: String,
    @Json(name = "PreviousDate") val previousDate: String,
    @Json(name = "PreviousURL") val previousUrl: String,
    @Json(name = "Timestamp") val timestamp: String,
    @Json(name = "Valute") val valutes: Map<String, Valute>
)
