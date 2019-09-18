package com.sergei.news.repository.source

import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.SourceRepository
import com.sergei.news.service.NetworkService
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class RemoteSourceRepository(
    private val mNetworkService: NetworkService
) : SourceRepository {

    override fun getSource(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Flowable<List<SourcesResponse.Source>> {
        return mNetworkService
            .getSources(params)
            .map {
                it.body()?.sources ?: emptyList()
            }
    }
}