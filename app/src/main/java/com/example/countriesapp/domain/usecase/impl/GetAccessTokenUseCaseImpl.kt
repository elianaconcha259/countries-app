package com.example.countriesapp.domain.usecase.impl

import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.domain.repository.CountryRepository
import com.example.countriesapp.domain.usecase.GetAccessTokenUseCase
import com.example.countriesapp.domain.usecase.GetCountriesUseCase
import com.example.countriesapp.util.common.AsyncResult
import javax.inject.Inject

class GetAccessTokenUseCaseImpl @Inject constructor(private val countryRepository: CountryRepository) :
    GetAccessTokenUseCase {

    override suspend operator fun invoke(): AsyncResult<String> =
        countryRepository.getAccessToken()

}
