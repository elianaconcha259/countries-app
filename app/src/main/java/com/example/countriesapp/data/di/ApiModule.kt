package com.example.countriesapp.data.di


import com.example.countriesapp.data.datasource.remote.network.api.ApiProvider
import com.example.countriesapp.data.datasource.remote.network.api.ApiTokenProvider
import com.example.countriesapp.data.datasource.remote.network.api.CountryApi
import com.example.countriesapp.data.datasource.remote.network.api.GetAccessTokenApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideCountryApi(provider: ApiProvider): CountryApi =
        provider.create(CountryApi::class.java)

    @Provides
    @Singleton
    fun provideGetAccessTokenApi(provider: ApiTokenProvider): GetAccessTokenApi =
        provider.create(GetAccessTokenApi::class.java)
}
