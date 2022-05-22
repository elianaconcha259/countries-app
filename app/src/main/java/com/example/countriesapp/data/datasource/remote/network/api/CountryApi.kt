package com.example.countriesapp.data.datasource.remote.network.api

import com.example.countriesapp.data.datasource.remote.network.dto.CountryDTO
import com.example.countriesapp.data.datasource.remote.network.url.URL
import retrofit2.http.*

interface CountryApi {

    @GET(URL.COUNTRIES)
    suspend fun getCountries(@Header("Authorization") apiToken: String): List<CountryDTO>

}
