package com.example.countriesapp.domain.repository

import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.util.common.AsyncResult

interface CountryRepository {

    suspend fun getCountries(apiToken: String): AsyncResult<List<CountryModel>>

    suspend fun getAccessToken(): AsyncResult<String>

}