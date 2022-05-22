package com.example.countriesapp.presentation.ui.country

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.domain.usecase.GetCountriesUseCase
import com.example.countriesapp.util.common.AsyncResult
import com.example.countriesapp.util.common.Errors
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountryViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var getCountriesUseCase: GetCountriesUseCase

    @InjectMockKs
    private lateinit var viewModel: CountryViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers should call getUsersUseCase()`() =
        runBlockingTest {
            // Given
            val data = listOf(mockk<CountryModel>())
            val expectedResult = AsyncResult.success(data)

            coEvery { getCountriesUseCase("12345") } returns expectedResult

            // When
            viewModel.getCountries("12345")

            // Then
            coVerify { getCountriesUseCase("12345") }
        }

    @Test
    fun `getUsers should emit ShowCountries with data status through a liveData when getCountriesUseCase returns success`() =
        runBlockingTest {
            // Given
            val data = listOf(mockk<CountryModel>())
            val expectedResult = AsyncResult.success(data)

            coEvery { getCountriesUseCase("12345") } returns expectedResult

            // When
            viewModel.getCountries("12345")
            val result = viewModel.viewState.value

            // Then
            assertEquals(ShowCountries(data), result)
        }

    @Test
    fun `getUsers should emit ShowError with data status through a liveData when getCountriesUseCase returns success but data is null`() =
        runBlockingTest {
            // Given
            val data = null
            val expectedResult = AsyncResult.success(data)

            coEvery { getCountriesUseCase("12345") } returns expectedResult

            // When
            viewModel.getCountries("12345")
            val result = viewModel.viewState.value

            // Then
            assertEquals(ShowError(Errors.UnknownError), result)
        }

    @Test
    fun `getUsers should emit ShowError with data status through a liveData when getCountriesUseCase returns error`() =
        runBlockingTest {
            // Given
            val error = Errors.NetworkError
            val expectedResult: AsyncResult<List<CountryModel>> = AsyncResult.error(error)

            coEvery { getCountriesUseCase("12345") } returns expectedResult

            // When
            viewModel.getCountries("12345")
            val result = viewModel.viewState.value

            // Then
            assertEquals(ShowError(error), result)
        }

}
