package com.sergei.news.di

import com.sergei.news.repository.EverythingRepository
import com.sergei.news.repository.SourceEverythingRepository
import com.sergei.news.repository.combine.BufferedSourceEverythingRepository
import com.sergei.news.repository.combine.RemoteSourceEverythingRepository
import com.sergei.news.repository.everything.EverythingRemoteRepository
import com.sergei.news.repository.source.LocalSourceRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single {
        EverythingRemoteRepository(get())
    } bind EverythingRepository::class


    single {
        RemoteSourceEverythingRepository(get(), get())
    } bind SourceEverythingRepository::class
}