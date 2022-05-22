package com.example.countriesapp.presentation.di

import com.example.countriesapp.common.error.ErrorMessage
import com.example.countriesapp.util.common.error.ErrorMessageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationModule {

    @Binds
    @Singleton
    abstract fun bindErrorMessage(errorMessageImpl: ErrorMessageImpl): ErrorMessage
}
