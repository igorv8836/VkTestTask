package com.example.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.CurrencyDao
import com.example.database.entity.CurrencyTable

@Database(
    version = 1,
    entities = [
        CurrencyTable::class
    ],
    exportSchema = false
)
internal abstract class AppDatabase: RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
}