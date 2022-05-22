package com.example.countriesapp.data.datasource.local

import com.example.countriesapp.data.datasource.local.db.entity.CountryEntity

interface LocalDataSource {

    suspend fun getCountries(): List<CountryEntity>

    suspend fun saveCountries(users :List<CountryEntity>)

}
