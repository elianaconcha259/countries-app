package com.example.countriesapp.domain.usecase

import com.example.countriesapp.domain.repository.CountryRepository
import com.example.countriesapp.util.common.AsyncResult
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAccessTokenUseCaseImplTest {

    @MockK
    lateinit var countryRepository: CountryRepository

    @InjectMockKs
    lateinit var getAccessTokenUseCase: GetAccessTokenUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getAccessToken() should call getAccessToken()`() =
        runBlockingTest {
            // Given
            val data = "12345"
            val expectedResult = AsyncResult.success("12345")

            coEvery { countryRepository.getAccessToken() } returns expectedResult

            // When
            getAccessTokenUseCase

            // Then
            coVerify { countryRepository.getAccessToken() }
        }

}
