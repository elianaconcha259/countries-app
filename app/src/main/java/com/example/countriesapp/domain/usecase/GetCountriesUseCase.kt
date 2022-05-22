package com.example.countriesapp.domain.usecase

import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.util.common.AsyncResult

interface GetCountriesUseCase {
    suspend operator fun invoke(apiToken: String): AsyncResult<List<CountryModel>>
}