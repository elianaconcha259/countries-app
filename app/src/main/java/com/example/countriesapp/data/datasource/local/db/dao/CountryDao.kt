package com.example.countriesapp.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countriesapp.data.datasource.local.db.entity.CountryEntity

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countries: List<CountryEntity>)

    @Query("SELECT * FROM country")
    suspend fun getAll(): List<CountryEntity>
    }
