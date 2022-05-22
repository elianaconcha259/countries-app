package com.example.countriesapp.data.datasource.remote.network.api

import com.example.countriesapp.data.datasource.remote.network.url.URL
import com.example.countriesapp.util.common.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiTokenProvider @Inject constructor() {

    private val retrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        val httpClient = OkHttpClient().newBuilder().apply { connectTimeout(60, TimeUnit.SECONDS)
        addInterceptor(logging)
            addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val requestBuild = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("api-token", Constants.API_TOKEN)
                    .addHeader("user-email", Constants.EMAIL_FOR_TOKEN)
                val original = requestBuild.build()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            })}.build()
        Retrofit.Builder()
            .baseUrl(URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient).build()
    }

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)
}
