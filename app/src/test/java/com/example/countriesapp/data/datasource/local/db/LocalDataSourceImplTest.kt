package com.example.countriesapp.data.datasource.local.db

import com.example.countriesapp.data.datasource.local.LocalDataSourceImpl
import com.example.countriesapp.data.datasource.local.db.dao.CountryDao
import com.example.countriesapp.data.datasource.local.db.entity.CountryEntity
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalDataSourceImplTest {

    @MockK
    lateinit var countryDao: CountryDao

    @InjectMockKs
    lateinit var localDataSource: LocalDataSourceImpl


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getCountries() should call getAll()`() =
        runBlockingTest {
            // Given
            val expectedResultCountryEntity = mockk<List<CountryEntity>>()

            coEvery { countryDao.getAll() } returns expectedResultCountryEntity

            // When
            localDataSource.getCountries()

            // Then
            coVerify { countryDao.getAll() }
        }

    @Test
    fun `saveCountries() should call insert()`() =
        runBlockingTest {
            // Given
            val expectedResult = mockk<List<CountryEntity>>()

            coEvery { countryDao.insert(expectedResult) } just Runs

            // When
            localDataSource.saveCountries(expectedResult)

            // Then
            coVerify { countryDao.insert(expectedResult) }
        }
    }
