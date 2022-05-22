package com.example.countriesapp.presentation.ui.country

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.domain.usecase.GetCountriesUseCase
import com.example.countriesapp.presentation.di.IoDispatcher
import com.example.countriesapp.util.common.AsyncResult
import com.example.countriesapp.util.common.AsyncResultStatus
import com.example.countriesapp.util.common.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var viewState: MutableLiveData<CountryViewState> = MutableLiveData()

    fun getCountries(apiToken: String) = viewModelScope.launch(dispatcher) {
        viewState.postValue(ShowLoading)
        var response = getCountriesUseCase(apiToken)
        val result = processResponse(response)
        viewState.postValue(result)
    }

    private fun processResponse(response: AsyncResult<List<CountryModel>>): CountryViewState {
        return when (response.status) {
            AsyncResultStatus.SUCCESS -> response.data?.let { ShowCountries(it) }
                ?: ShowError(Errors.UnknownError)
            AsyncResultStatus.ERROR -> ShowError(response.error ?: Errors.UnknownError)
        }
    }

}