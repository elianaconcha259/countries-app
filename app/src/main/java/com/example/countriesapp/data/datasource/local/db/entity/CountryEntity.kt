package com.example.countriesapp.data.datasource.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "country",
)
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val country_name: String,
    val country_short_name: String,
    val country_phone_code: Int
)