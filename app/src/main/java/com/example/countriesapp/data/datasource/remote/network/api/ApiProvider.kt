package com.example.countriesapp.data.datasource.remote.network.api

import android.content.Context
import android.util.Log
import com.example.countriesapp.common.PrefsHelper.accessToken
import com.example.countriesapp.data.datasource.remote.network.url.URL
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import com.example.countriesapp.common.PrefsHelper.customPreference

@Singleton
class ApiProvider @Inject constructor() {

    private val retrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        val httpClient = OkHttpClient().newBuilder().apply { connectTimeout(60, TimeUnit.SECONDS)
        addInterceptor(logging)}.build()
        Retrofit.Builder()
            .baseUrl(URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient).build()
    }

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)
}
