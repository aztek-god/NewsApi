package com.sergei.news.viewmodel

import com.sergei.news.extension.compose.BackgroundSingleTransformer
import com.sergei.news.extension.logd
import com.sergei.news.repository.SourceRepository
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel

class SourcesViewModel(private val mSourceRepository: SourceRepository) :
    SingleLiveDataViewModel<SourcesViewModel>() {

    fun loadSources() {
        val disposable = mSourceRepository
            .getSource(mapOf(), 2, 10)
            .compose(BackgroundSingleTransformer())
            .subscribe(
                {
                    logd("sources = $it")
                },
                {
                    logd("error = $it")
                }
            )

        addDisposable(disposable)
    }
}