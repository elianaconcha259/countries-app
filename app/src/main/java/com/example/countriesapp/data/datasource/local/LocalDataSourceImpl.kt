package com.example.countriesapp.data.datasource.local

import com.example.countriesapp.data.datasource.local.db.dao.CountryDao
import com.example.countriesapp.data.datasource.local.db.entity.CountryEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val countryDao: CountryDao) :
    LocalDataSource {

    override suspend fun getCountries(): List<CountryEntity> =
        countryDao.getAll()

    override suspend fun saveCountries(countries: List<CountryEntity>) =
        countryDao.insert(countries)

}
