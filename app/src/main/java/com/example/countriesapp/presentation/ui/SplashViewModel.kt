package com.example.countriesapp.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.domain.usecase.GetAccessTokenUseCase
import com.example.countriesapp.presentation.di.IoDispatcher
import com.example.countriesapp.util.common.AsyncResult
import com.example.countriesapp.util.common.AsyncResultStatus
import com.example.countriesapp.util.common.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var viewState: MutableLiveData<SplashViewState> = MutableLiveData()

    fun getAccessToken() = viewModelScope.launch(dispatcher) {
        viewState.postValue(ShowLoading)
        var response = getAccessTokenUseCase()
        val result = processResponse(response)
        viewState.postValue(result)
    }

    private fun processResponse(response: AsyncResult<String>): SplashViewState {
        return when (response.status) {
            AsyncResultStatus.SUCCESS -> response.data?.let { Continue(it) }
                ?: ShowError(Errors.UnknownError)
            AsyncResultStatus.ERROR -> ShowError(response.error ?: Errors.UnknownError)
        }
    }

}