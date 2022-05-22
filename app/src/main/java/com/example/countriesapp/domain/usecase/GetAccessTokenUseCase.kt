package com.example.countriesapp.domain.usecase

import com.example.countriesapp.util.common.AsyncResult

interface GetAccessTokenUseCase {
    suspend operator fun invoke(): AsyncResult<String>
}