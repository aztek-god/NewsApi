package com.sergei.news.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sergei.news.BuildConfig
import com.sergei.news.service.NetworkService
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single {
        Cache(androidApplication().cacheDir, 10 * 1024)
    }

    single {
        OkHttpClient
            .Builder()
            .cache(get())
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder =
                    originalRequest.newBuilder().header("X-Api-Key", BuildConfig.BASE_API_KEY)
                val newRequest = requestBuilder.build()
                chain.proceed(newRequest)
            }
            .build()
    }

    single {
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        GsonConverterFactory.create(get<Gson>())
    }

    single {
        GsonBuilder().setLenient().create()
    }

    single {
        get<Retrofit>().create(NetworkService::class.java)
    }
}