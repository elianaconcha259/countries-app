package com.example.countriesapp.domain.usecase

import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.domain.repository.CountryRepository
import com.example.countriesapp.domain.usecase.impl.GetCountriesUseCaseImpl
import com.example.countriesapp.util.common.AsyncResult
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCountriesUseCaseImplTest {

    @MockK
    lateinit var countryRepository: CountryRepository

    @InjectMockKs
    lateinit var getCountriesUseCase: GetCountriesUseCaseImpl


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsersUseCase() should call getCountries()`() =
        runBlockingTest {
            // Given
            val data = mockk<List<CountryModel>>()
            val expectedResult = AsyncResult.success(data)

            coEvery { countryRepository.getCountries("12345") } returns expectedResult

            // When
            getCountriesUseCase("12345")

            // Then
            coVerify { countryRepository.getCountries("12345") }
        }

}
