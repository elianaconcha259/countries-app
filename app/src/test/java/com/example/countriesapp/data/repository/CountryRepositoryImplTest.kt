package com.example.countriesapp.data.repository

import com.example.countriesapp.data.datasource.local.LocalDataSource
import com.example.countriesapp.data.datasource.local.db.entity.CountryEntity
import com.example.countriesapp.data.datasource.remote.RemoteDataSource
import com.example.countriesapp.data.datasource.remote.network.dto.CountryDTO
import com.example.countriesapp.data.datasource.remote.network.dto.GetAccesTokenDTO
import com.example.countriesapp.data.mapper.fromListCountryDTOToListCountryEntity
import com.example.countriesapp.data.mapper.fromListCountryEntityToListCountryModel
import com.example.countriesapp.domain.model.CountryModel
import com.example.countriesapp.util.common.AsyncResult
import com.example.countriesapp.util.common.Errors
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.lang.Exception

@ExperimentalCoroutinesApi
class CountryRepositoryImplTest {

    @MockK
    lateinit var remoteDataSource: RemoteDataSource

    @MockK
    lateinit var localDataSource: LocalDataSource

    @InjectMockKs
    lateinit var countryRepositoryImpl: CountryRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getCountries() should call remoteDataSource getCountries() and localDataSource saveCountries() and localDataSource getCountries()`() =
        runBlockingTest {
            // Given
            val dbResult = listOf<CountryEntity>()
            val modelResult = listOf<CountryModel>()

            coEvery { remoteDataSource.getCountries("12345").fromListCountryDTOToListCountryEntity() } returns dbResult
            coEvery { localDataSource.saveCountries(dbResult) } just Runs
            coEvery { localDataSource.getCountries().fromListCountryEntityToListCountryModel() } returns modelResult

            // When
            countryRepositoryImpl.getCountries("12345")

            // Then
            coVerify { remoteDataSource.getCountries("12345") }
            coVerify {localDataSource.saveCountries(dbResult)}
            coVerify { localDataSource.getCountries() }
        }

    @Test
    fun `getCountries() should return success when is success`() =
        runBlockingTest {
            // Given
            val apiResult = listOf<CountryDTO>()
            val resultFromDB = listOf<CountryEntity>()
            val expectedResult = AsyncResult.success(resultFromDB)

            coEvery { remoteDataSource.getCountries("12345") } returns apiResult
            coEvery { localDataSource.saveCountries(apiResult.fromListCountryDTOToListCountryEntity()) } just Runs
            coEvery { localDataSource.getCountries() } returns resultFromDB

            // When
            val result = countryRepositoryImpl.getCountries("12345")

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `getCountries() should return error with UnknownError when there is error`() =
        runBlockingTest {
            // Given
            val expectedResult: AsyncResult<List<CountryDTO>> = AsyncResult.error(Errors.UnknownError)

            coEvery { remoteDataSource.getCountries("12345") }.throws(Exception())

            // When
            val result = countryRepositoryImpl.getCountries("12345")

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `getCountries() should return error with NetworkError when there is error`() =
        runBlockingTest {
            // Given
            val expectedResult: AsyncResult<List<String>> = AsyncResult.error(Errors.UnknownError)

            coEvery { remoteDataSource.getCountries("12345") }.throws(IOException())

            // When
            val result = countryRepositoryImpl.getCountries("12345")

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `getAccessToken() should call remoteDataSource getAccessToken()`() =
        runBlockingTest {
            // Given
            var result : GetAccesTokenDTO = GetAccesTokenDTO("12345", "")

           coEvery { remoteDataSource.getAccessToken() } returns result

            // When
            countryRepositoryImpl.getAccessToken()

            // Then
            coVerify { remoteDataSource.getAccessToken() }
        }

    @Test
    fun `getAccessToken() should return success when is success`() =
        runBlockingTest {
            // Given
            var resultAccess : GetAccesTokenDTO = GetAccesTokenDTO("12345", "")
            val data = "12345"
            val expectedResult = AsyncResult.success(data)

            coEvery { remoteDataSource.getAccessToken() } returns resultAccess

            // When
            val result = countryRepositoryImpl.getAccessToken()

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `getAccessToken() should return error with NetworkError when there is error`() =
        runBlockingTest {
            // Given
            val expectedResult: AsyncResult<List<String>> = AsyncResult.error(Errors.NetworkError)

            coEvery { remoteDataSource.getAccessToken() }.throws(IOException())

            // When
            val result = countryRepositoryImpl.getAccessToken()

            // Then
            assertEquals(expectedResult, result)
        }

}
