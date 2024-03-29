package com.sergei.news.viewmodel

import com.sergei.news.extension.compose.BackgroundFlowableTransformer
import com.sergei.news.model.util.BottomProgress
import com.sergei.news.model.util.EverythingSourceModel
import com.sergei.news.repository.SourceEverythingRepository
import com.sergei.news.util.DiffUtilItem
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel

class SourceEverythingViewModel(private val mSource: SourceEverythingRepository) :
    SingleLiveDataViewModel<List<DiffUtilItem>>() {

    var mCurrentPage: Int = INITIAL_PAGE
        private set

    var isBlocked = false
        private set

    init {
        load()
    }

    fun load() {
        isBlocked = true

        if (!isBlocked) {
            val disposable = mSource
                .loadSourceEverything(mapOf(), mCurrentPage, PAGE_SIZE)
                .compose(BackgroundFlowableTransformer())
                .doOnComplete { isBlocked = false }
                .subscribe(
                    {
                        outcomeSuccess(prepareData(it))
                        mCurrentPage++
                    },
                    {
                        outcomeFailure(it)
                        mCurrentPage = INITIAL_PAGE
                    }
                )

            addDisposable(disposable)
        }
    }

    private fun prepareData(modelList: List<EverythingSourceModel>): List<DiffUtilItem> {
        return modelList + BottomProgress
    }

    private companion object {
        const val INITIAL_PAGE = 1
        const val PAGE_SIZE = 20
    }
}