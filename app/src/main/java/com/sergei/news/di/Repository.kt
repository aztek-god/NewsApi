package com.sergei.news.di

import com.sergei.news.repository.SourceEverythingRepository
import com.sergei.news.repository.SourceRepository
import com.sergei.news.repository.combine.BufferedSourceEverythingRepository
import com.sergei.news.repository.combine.RemoteSourceEverythingRepository
import com.sergei.news.repository.everything.EverythingRemoteRepository
import com.sergei.news.repository.source.LocalSourceRepository
import com.sergei.news.repository.source.RemoteSourceRepository
import com.sergei.news.service.NetworkService
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    single {
        LocalSourceRepository(
            RemoteSourceRepository(get()),
            get()
        )
    } bind SourceRepository::class

    single {
        BufferedSourceEverythingRepository(
            RemoteSourceEverythingRepository(
                get<SourceRepository>(),
                EverythingRemoteRepository(get<NetworkService>())
            )
        )
    } bind SourceEverythingRepository::class
}