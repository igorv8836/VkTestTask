package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.dao.CurrencyDao
import com.example.database.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> { provideDatabase(get()) }
    factory<CurrencyDao> { get<AppDatabase>().getCurrencyDao() }
}

internal fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "database")
        .fallbackToDestructiveMigration()
        .build()
}
