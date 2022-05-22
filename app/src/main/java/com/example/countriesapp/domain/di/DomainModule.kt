package com.example.countriesapp.domain.di

import com.example.countriesapp.domain.usecase.GetAccessTokenUseCase
import com.example.countriesapp.domain.usecase.GetCountriesUseCase
import com.example.countriesapp.domain.usecase.impl.GetAccessTokenUseCaseImpl
import com.example.countriesapp.domain.usecase.impl.GetCountriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule{

    @Binds
    @Singleton
    abstract fun bindGetCountriesUseCase(getCountriesUseCaseImpl: GetCountriesUseCaseImpl): GetCountriesUseCase

    @Binds
    @Singleton
    abstract fun bindGetAccessTokenUseCase(getAccessTokenUseCase: GetAccessTokenUseCaseImpl): GetAccessTokenUseCase

}