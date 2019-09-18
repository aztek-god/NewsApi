package com.sergei.news.viewmodel

import com.sergei.news.extension.compose.BackgroundSingleTransformer
import com.sergei.news.extension.logd
import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.SourceRepository
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel

class SourcesViewModel(private val mSourceRepository: SourceRepository) :
    SingleLiveDataViewModel<List<SourcesResponse.Source>>() {

    private val mData: MutableList<SourcesResponse.Source> = mutableListOf()

    private var isLoading = false

    var mCurrentPage: Int = 1
        private set

    init {
        loadSources()
    }

    fun loadSources() {
        if (!isLoading) {
            logd("doOnSubscribe::!isLoading = $isLoading", "sdvskidex@mail.ru")

            val disposable = mSourceRepository
                .getSource(mapOf(), mCurrentPage, PAGE_SIZE)
                .compose(BackgroundSingleTransformer())
                .doOnSubscribe {
                    isLoading = true
                    logd("doOnSubscribe::isLoading = $isLoading", "sdvskidex@mail.ru")
                    outcomeProgress(true)
                }
                .doOnEvent { _, _ ->
                    outcomeProgress(false)
                }
                .doFinally {
                    isLoading = false
                    logd("doFinally::isLoading = $isLoading", "sdvskidex@mail.ru")
                }
                .subscribe(
                    {
                        mCurrentPage++
                        mData += it
                        outcomeSuccess(mData)
                    },
                    {
                        mCurrentPage = 1
                        outcomeFailure(it)
                    }
                )

            addDisposable(disposable)
        }
    }

    companion object {
        private const val PAGE_SIZE = 5
    }
}