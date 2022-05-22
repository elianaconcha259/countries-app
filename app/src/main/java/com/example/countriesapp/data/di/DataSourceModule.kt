package com.example.countriesapp.data.di

import com.example.countriesapp.data.datasource.local.LocalDataSource
import com.example.countriesapp.data.datasource.local.LocalDataSourceImpl
import com.example.countriesapp.data.datasource.remote.RemoteDataSource
import com.example.countriesapp.data.datasource.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}
