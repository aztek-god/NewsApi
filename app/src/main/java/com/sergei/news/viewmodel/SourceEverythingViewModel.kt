package com.sergei.news.viewmodel

import com.sergei.news.extension.compose.BackgroundFlowableTransformer
import com.sergei.news.extension.compose.BackgroundSingleTransformer
import com.sergei.news.extension.logd
import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.SourceEverythingRepository
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SourceEverythingViewModel(private val mSource: SourceEverythingRepository) :
    SingleLiveDataViewModel<Pair<SourcesResponse.Source, List<EverythingResponse.Article>>>() {

    fun load() {
        val disposable = mSource
            .loadSourceEverything(mapOf(), 1, 20)
            .compose(BackgroundFlowableTransformer())
            .subscribe(
                {
                    logd("result = $it")
                },
                {
                    logd("result = $it")
                }
            )

        addDisposable(disposable)
    }
}