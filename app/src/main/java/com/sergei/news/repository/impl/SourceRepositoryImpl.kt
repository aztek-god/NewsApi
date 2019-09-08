package com.sergei.news.repository.impl

import com.sergei.news.database.SourceDao
import com.sergei.news.database.sourceIsEmpty
import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.SourceRepository
import com.sergei.news.service.NetworkService
import io.reactivex.Single

class SourceRepositoryImpl(
    private val mNetworkService: NetworkService,
    private val mSourceDao: SourceDao
) : SourceRepository {

    override fun getSource(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Single<List<SourcesResponse.Source>> {
        val network: Single<List<SourcesResponse.Source>> by lazy {
            mNetworkService
                .getSources(params)
                .map {
                    it.body()?.sources ?: emptyList()
                }
        }

        return mSourceDao
            .sourceIsEmpty
            .flatMap { isEmpty ->
                if (isEmpty) {
                    network.flatMap { sourceList ->
                        mSourceDao.insertAll(sourceList)
                        mSourceDao.getAllLimit(pageSize, page)
                    }
                } else {
                    mSourceDao.getAllLimit(pageSize, page)
                }
            }
    }
}