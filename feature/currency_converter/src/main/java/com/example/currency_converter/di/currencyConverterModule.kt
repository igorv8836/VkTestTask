package com.example.currency_converter.di

import com.example.currency_converter.data.repository.CurrencyRepositoryImpl
import com.example.currency_converter.domain.repository.CurrencyRepository
import com.example.currency_converter.domain.usecase.ConvertCurrencyUseCase
import com.example.currency_converter.domain.usecase.ConvertCurrencyUseCaseImpl
import com.example.currency_converter.domain.usecase.GetCurrenciesListUseCase
import com.example.currency_converter.domain.usecase.GetCurrenciesListUseCaseImpl
import com.example.currency_converter.presentation.viewmodel.CurrencyConverterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val currencyConverterModule = module {
    factory<CurrencyRepository> { CurrencyRepositoryImpl(get(), get()) }
    factory<ConvertCurrencyUseCase> { ConvertCurrencyUseCaseImpl(get()) }
    factory<GetCurrenciesListUseCase> { GetCurrenciesListUseCaseImpl(get()) }

    viewModel { CurrencyConverterViewModel(get(), get()) }
}