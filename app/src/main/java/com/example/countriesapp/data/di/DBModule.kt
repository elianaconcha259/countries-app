package com.example.countriesapp.data.di

import com.example.countriesapp.data.datasource.local.db.DBProvider
import com.example.countriesapp.data.datasource.local.db.dao.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideCountryDao(dbProvider: DBProvider): CountryDao = dbProvider.database.countryDao()

}
