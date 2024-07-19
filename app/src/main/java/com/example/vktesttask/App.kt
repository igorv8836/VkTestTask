package com.example.vktesttask

import android.app.Application
import com.example.common.di.commonModule
import com.example.currency_converter.di.currencyConverterModule
import com.example.database.di.databaseModule
import com.example.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                commonModule,
                networkModule,
                databaseModule,
                currencyConverterModule
            )
        }
    }
}