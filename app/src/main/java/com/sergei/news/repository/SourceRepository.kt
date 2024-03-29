package com.sergei.news.repository

import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.SourcesResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.ref.Reference
import java.lang.ref.SoftReference

interface SourceRepository {
    fun getSource(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Flowable<List<SourcesResponse.Source>>
}






