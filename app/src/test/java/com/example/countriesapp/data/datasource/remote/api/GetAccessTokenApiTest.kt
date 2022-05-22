package com.example.countriesapp.data.datasource.remote.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countriesapp.data.datasource.remote.network.api.ApiTokenProvider
import com.example.countriesapp.data.datasource.remote.network.api.GetAccessTokenApi
//import androidx.test.runners.AndroidJUnit4
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
class GetAccessTokenApiTest {

    @get:Rule
    var instantTask = InstantTaskExecutorRule()

    private lateinit var api: GetAccessTokenApi

    @Before
    fun `create product api`() {
        api = ApiTokenProvider().create(GetAccessTokenApi::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun `verify response getAccessToken is not null`() = runBlocking {
        val response = api.getAccessToken()
        assertTrue(response!=null)
    }

}