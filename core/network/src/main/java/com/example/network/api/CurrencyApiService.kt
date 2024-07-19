package com.example.network.api

import com.example.network.dto.CurrencyResponse
import retrofit2.http.GET

interface CurrencyApiService {
    @GET("daily_json.js")
    suspend fun getCurrency(): CurrencyResponse
}