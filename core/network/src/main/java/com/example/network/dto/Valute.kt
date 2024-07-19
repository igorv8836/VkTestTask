package com.example.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Valute(
    @Json(name = "ID") val id: String,
    @Json(name = "NumCode") val numCode: String,
    @Json(name = "CharCode") val charCode: String,
    @Json(name = "Nominal") val nominal: Int,
    @Json(name = "Name") val name: String,
    @Json(name = "Value") val value: Double,
    @Json(name = "Previous") val previous: Double
)