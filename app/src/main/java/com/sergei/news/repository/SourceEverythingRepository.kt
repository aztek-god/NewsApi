package com.sergei.news.repository

import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.EverythingSourceModel
import com.sergei.news.model.SourcesResponse
import io.reactivex.Flowable

interface SourceEverythingRepository {
    fun loadSourceEverything(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Flowable<List<EverythingSourceModel>>
}