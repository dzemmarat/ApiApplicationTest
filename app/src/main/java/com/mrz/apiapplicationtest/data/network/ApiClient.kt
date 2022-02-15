package com.mrz.apiapplicationtest.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.moczul.ok2curl.CurlInterceptor
import com.mrz.apiapplicationtest.data.network.api.NotesApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(CurlInterceptor { Timber.i(it) })
        .build()

    fun retrofitService(): NotesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()).asLenient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(NotesApi::class.java)
    }
}