package com.sergei.news

import android.app.Application
import com.sergei.news.di.database
import com.sergei.news.di.networkModule
import com.sergei.news.di.repositoryModule
import com.sergei.news.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            this.androidContext(this@App)
            modules(listOf(networkModule, viewModelModule, database, repositoryModule))
        }
    }
}