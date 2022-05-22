package com.example.countriesapp.data.datasource.remote

import com.example.countriesapp.data.datasource.remote.network.api.CountryApi
import com.example.countriesapp.data.datasource.remote.network.api.GetAccessTokenApi
import com.example.countriesapp.data.datasource.remote.network.dto.CountryDTO
import com.example.countriesapp.data.datasource.remote.network.dto.GetAccesTokenDTO
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: CountryApi, private val getAccessTokenApi: GetAccessTokenApi) : RemoteDataSource {

    override suspend fun getCountries(apiToken: String): List<CountryDTO> = api.getCountries(apiToken)

    override suspend fun getAccessToken(): GetAccesTokenDTO = getAccessTokenApi.getAccessToken()
}
