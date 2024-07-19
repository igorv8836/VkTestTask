package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyTable(
    @PrimaryKey
    val charCode: String,
    val name: String,
    val nominal: Int,
    val value: Double,
    val timestamp: String
)