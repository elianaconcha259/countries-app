package com.example.countriesapp.data.datasource.remote

import com.example.countriesapp.data.datasource.remote.network.api.CountryApi
import com.example.countriesapp.data.datasource.remote.network.api.GetAccessTokenApi
import com.example.countriesapp.data.datasource.remote.network.dto.CountryDTO
import com.example.countriesapp.data.datasource.remote.network.dto.GetAccesTokenDTO
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest {

    @MockK
    lateinit var countryApi: CountryApi

    @MockK
    lateinit var getAccessTokenApi: GetAccessTokenApi

    @InjectMockKs
    lateinit var remoteDataSource: RemoteDataSourceImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getCountries() should call getCountries()`() =
        runBlockingTest {
            // Given
            val listCountryDto = listOf<CountryDTO>()

            coEvery { countryApi.getCountries("12345") } returns listCountryDto

            // When
            remoteDataSource.getCountries("12345")

            // Then
            coVerify { countryApi.getCountries("12345") }
        }

    @Test
    fun `getAccessToken() should call getAccessToken()`() =
        runBlockingTest {
            // Given
            val result = GetAccesTokenDTO("12345","")

            coEvery { getAccessTokenApi.getAccessToken() } returns result

            // When
            remoteDataSource.getAccessToken()

            // Then
            coVerify { getAccessTokenApi.getAccessToken() }
        }

}
