package com.example.countriesapp.data.datasource.remote.api

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countriesapp.data.datasource.local.db.dao.CountryDao
import com.example.countriesapp.data.datasource.remote.network.api.ApiProvider
import com.example.countriesapp.data.datasource.remote.network.api.CountryApi
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
//import androidx.test.runners.AndroidJUnit4
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering

@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
class CountryApiTest {

    @get:Rule
    var instantTask = InstantTaskExecutorRule()

    private lateinit var api: CountryApi

    @Before
    fun `create product api`() {
        api = ApiProvider().create(CountryApi::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun `verify response get countries is not null`() = runBlocking {
        val response = api.getCountries("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJlbGlhbmFjMjU5QGdtYWlsLmNvbSIsImFwaV90b2tlbiI6ImJmRTE5S043RmJLWTJHc1BnSDBBeFpZaVlCSzFRRmF1RGhZeDRyNzg2aEk0MDExcHJPbWdiWTFjN002T3lfaHdfdGMifSwiZXhwIjoxNjUzMjcyNTk2fQ.wrKay_yMdq6uTVBUoqoNmjfh-B_MQ-z2ZD5uYSzr5j4")
        assertFalse(response.isNullOrEmpty())
    }

}