package com.example.countriesapp.data.datasource.remote.network.api

import com.example.countriesapp.data.datasource.remote.network.dto.CountryDTO
import com.example.countriesapp.data.datasource.remote.network.dto.GetAccesTokenDTO
import com.example.countriesapp.data.datasource.remote.network.url.URL

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetAccessTokenApi {

    @GET(URL.ACCESS_TOKEN)
    suspend fun getAccessToken(): GetAccesTokenDTO
}
