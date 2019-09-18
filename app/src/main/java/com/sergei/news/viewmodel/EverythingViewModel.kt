package com.sergei.news.viewmodel

import com.sergei.news.extension.compose.BackgroundSingleTransformer
import com.sergei.news.model.EverythingResponse
import com.sergei.news.service.NetworkService
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EverythingViewModel(private val mNetworkService: NetworkService) :
    SingleLiveDataViewModel<EverythingResponse>() {
    init {
        val disposable = mNetworkService
            .getEverything(
                mapOf(
                    "pageSize" to "10",
                    "page" to "1",
                    "sources" to "abc-news"
                )
            )
            .doOnSubscribe { outcomeProgress(true) }
            .doFinally { outcomeProgress(false) }
//            .compose(BackgroundSingleTransformer())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    response.body()?.let { outcomeSuccess(it) }
                },
                {
                    outcomeFailure(it)
                }
            )

        addDisposable(disposable)
    }
}