package com.example.countriesapp.data.repository

import com.example.countriesapp.data.datasource.local.LocalDataSource
import com.example.countriesapp.data.datasource.remote.RemoteDataSource
import com.example.countriesapp.data.mapper.fromListCountryDTOToListCountryEntity
import com.example.countriesapp.data.mapper.fromListCountryEntityToListCountryModel
import com.example.countriesapp.data.mapper.toCountryModel
import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.domain.repository.CountryRepository
import com.example.countriesapp.util.common.AsyncResult
import com.example.countriesapp.util.common.Errors
import java.io.IOException

import java.util.logging.Logger
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CountryRepository {

    private val logger = Logger.getLogger(CountryRepositoryImpl::class.simpleName)

    override suspend fun getAccessToken(): AsyncResult<String> = try {
        val authToken = remoteDataSource.getAccessToken().auth_token
        if (!authToken.isNullOrEmpty()) {
            AsyncResult.success(authToken)
        }else{
            AsyncResult.error(Errors.NetworkError)
        }
    }catch (e: IOException) {
        logger.warning(e.message)
        AsyncResult.error(Errors.NetworkError)
    }

    override suspend fun getCountries(apiToken: String): AsyncResult<List<CountryModel>> = try {
        var countriesFromLocal = localDataSource.getCountries().fromListCountryEntityToListCountryModel()
        if (countriesFromLocal.isNullOrEmpty()){
            val result = remoteDataSource.getCountries(apiToken)
            val resultFromApi = result.fromListCountryDTOToListCountryEntity()
            localDataSource.saveCountries(resultFromApi)
            countriesFromLocal = localDataSource.getCountries().fromListCountryEntityToListCountryModel()
        }
        AsyncResult.success(countriesFromLocal)
    }catch (e: Exception) {
        logger.warning(e.message)
        AsyncResult.error(Errors.UnknownError)
    }catch (e: IOException) {
        logger.warning(e.message)
        AsyncResult.error(Errors.NetworkError)
    }

}
