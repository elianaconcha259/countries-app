package com.example.countriesapp.data.datasource.remote

import com.example.countriesapp.data.datasource.remote.network.dto.CountryDTO
import com.example.countriesapp.data.datasource.remote.network.dto.GetAccesTokenDTO

interface RemoteDataSource {

    suspend fun getCountries(apiToken: String): List<CountryDTO>

    suspend fun getAccessToken(): GetAccesTokenDTO
}
