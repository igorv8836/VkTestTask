package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.database.entity.CurrencyTable
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: CurrencyTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyTable>)

    @Update
    suspend fun updateCurrency(currency: CurrencyTable)

    @Query("DELETE FROM currency WHERE charCode = :charCode")
    suspend fun deleteCurrency(charCode: String)

    @Query("DELETE FROM currency")
    suspend fun deleteAllCurrencies()

    @Query("SELECT * FROM currency WHERE charCode = :charCode")
    fun getCurrency(charCode: String): CurrencyTable

    @Query("SELECT * FROM currency")
    fun getAllCurrencies(): Flow<List<CurrencyTable>>


}