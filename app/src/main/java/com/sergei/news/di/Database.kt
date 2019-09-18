package com.sergei.news.di

import android.app.Application
import androidx.room.Room
import com.sergei.news.database.AppDatabase
import com.sergei.news.repository.SourceRepository
import com.sergei.news.repository.source.LocalSourceRepository
import com.sergei.news.repository.source.RemoteSourceRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val database = module {
    single {
        Room
            .databaseBuilder(get<Application>(), AppDatabase::class.java, "app-database")
            .build()
    }

    single {
        get<AppDatabase>().sourceDao()
    }

    single {
        LocalSourceRepository(
            RemoteSourceRepository(get()),
            get()
        )

    } bind SourceRepository::class
}