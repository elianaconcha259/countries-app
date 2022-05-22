package com.example.countriesapp.presentation.ui

import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.util.common.Errors

sealed class SplashViewState
data class Continue(val data: String) : SplashViewState()
object ShowLoading :  SplashViewState()
data class ShowError(val error: Errors) : SplashViewState()

