package com.sergei.news.repository.everything

import com.sergei.news.model.EverythingResponse
import com.sergei.news.repository.EverythingRepository
import com.sergei.news.service.NetworkService
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class EverythingRemoteRepository(private val mNetworkService: NetworkService) :
    EverythingRepository {

    override fun getEverything(params: Map<String, String>): Flowable<List<EverythingResponse.Article>> {
        return mNetworkService.getEverything(params).map { it.body()?.articles ?: emptyList() }
    }
}