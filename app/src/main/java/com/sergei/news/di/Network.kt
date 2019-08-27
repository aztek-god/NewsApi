package com.sergei.news.di

import com.google.gson.GsonBuilder
import com.sergei.news.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


val networkModule = module {
    single {
        Cache(androidApplication().cacheDir, 10 * 1024)
    }

    single {
        OkHttpClient
            .Builder()
            .cache(get())
            .addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", BuildConfig.BASE_API_KEY)
                    .build()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    single {
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        GsonBuilder().setLenient().create()
    }
}