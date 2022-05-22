package com.example.countriesapp.presentation.ui.country

import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.util.common.Errors

sealed class CountryViewState
data class ShowCountries(val data: List<CountryModel>) : CountryViewState()
object ShowLoading :  CountryViewState()
data class ShowError(val error: Errors) : CountryViewState()

