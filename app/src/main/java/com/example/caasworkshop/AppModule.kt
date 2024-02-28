package com.example.caasworkshop

import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofitService(): ApiService {
    // Adds an http header including your API key. Not necessary for all APIs

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", "live_3i3b2xtd2ZDKYif5QJHGBP8hLpbt4puoAqaSaaSqKMynyZDWdOMfV6aD0L5rivC2")
                .build()
            chain.proceed(request)
        }
        .build()
    //Creates API Service instance, connecting the GET endpoint to the established http request
    return Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}

val appModule = module {
    single { provideRetrofitService() }
    viewModel { MainViewModel(get()) }
}