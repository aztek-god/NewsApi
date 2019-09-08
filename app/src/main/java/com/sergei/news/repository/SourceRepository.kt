package com.sergei.news.repository

import com.sergei.news.model.SourcesResponse
import io.reactivex.Single

interface SourceRepository {
    fun getSource(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Single<List<SourcesResponse.Source>>
}

