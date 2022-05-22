package com.example.countriesapp.domain.usecase.impl

import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.domain.repository.CountryRepository
import com.example.countriesapp.domain.usecase.GetCountriesUseCase
import com.example.countriesapp.util.common.AsyncResult
import javax.inject.Inject

class GetCountriesUseCaseImpl @Inject constructor(private val countryRepository: CountryRepository) :
    GetCountriesUseCase {

    override suspend operator fun invoke(apiToken: String): AsyncResult<List<CountryModel>> =
        countryRepository.getCountries(apiToken)

}
