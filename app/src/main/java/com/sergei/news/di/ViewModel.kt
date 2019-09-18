package com.sergei.news.di

import com.sergei.news.viewmodel.EverythingViewModel
import com.sergei.news.viewmodel.SourceEverythingViewModel
import com.sergei.news.viewmodel.SourcesViewModel
import com.sergei.news.viewmodel.TopHeadsViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory {
        EverythingViewModel(get())
    }

    factory {
        SourcesViewModel(get())
    }

    factory {
        TopHeadsViewModel(get())
    }

    factory {
        SourceEverythingViewModel(get())
    }
}
