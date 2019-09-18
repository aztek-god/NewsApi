package com.sergei.news.viewmodel

import com.sergei.news.extension.compose.BackgroundFlowableTransformer
import com.sergei.news.extension.compose.BackgroundSingleTransformer
import com.sergei.news.extension.logd
import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.EverythingSourceModel
import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.SourceEverythingRepository
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SourceEverythingViewModel(private val mSource: SourceEverythingRepository) :
    SingleLiveDataViewModel<List<EverythingSourceModel>>() {

    init {
        val disposable = mSource
            .loadSourceEverything(mapOf(), 1, 20)
            .compose(BackgroundFlowableTransformer())
            .subscribe(
                {
                    outcomeSuccess(it)
                },
                {
                    outcomeFailure(it)
                }
            )

        addDisposable(disposable)

    }
}