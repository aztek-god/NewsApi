package com.sergei.news.repository

import com.sergei.news.model.util.EverythingSourceModel
import io.reactivex.Flowable

interface SourceEverythingRepository {
    fun loadSourceEverything(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Flowable<List<EverythingSourceModel>>
}