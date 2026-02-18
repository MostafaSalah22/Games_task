package com.example.bostatask.di

import com.example.bostatask.BuildConfig
import com.example.bostatask.data.remote.ApiKeyInterceptor
import com.example.bostatask.data.remote.RawgApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single { ApiKeyInterceptor(BuildConfig.RAWG_API_KEY) }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ApiKeyInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<RawgApi> {
        get<Retrofit>().create(RawgApi::class.java)
    }
}

const val BASE_URL = "https://api.rawg.io/api/"