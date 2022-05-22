package com.example.countriesapp.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.countriesapp.data.datasource.local.db.dao.CountryDao
import com.example.countriesapp.data.datasource.local.db.entity.CountryEntity

@Database(version = 1, entities = [CountryEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
}
