package com.example.network.di

import com.example.network.api.CurrencyApiService
import com.example.network.retrofit.RetrofitClient
import org.koin.dsl.module

val networkModule = module {
    single<RetrofitClient> { RetrofitClient() }

    factory<CurrencyApiService> { get<RetrofitClient>().instance.create(CurrencyApiService::class.java) }
}