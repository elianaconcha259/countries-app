package com.example.countriesapp.data.di

import com.example.countriesapp.data.repository.CountryRepositoryImpl
import com.example.countriesapp.domain.repository.CountryRepository
import dagger.Binds
import javax.inject.Singleton
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCountryRepository(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository


}
