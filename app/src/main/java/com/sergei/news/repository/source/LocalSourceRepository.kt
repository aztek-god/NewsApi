package com.sergei.news.repository.source

import com.sergei.news.database.SourceDao
import com.sergei.news.database.sourceIsEmpty
import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.SourceRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class LocalSourceRepository(
    private val mSourceRepository: SourceRepository,
    private val mSourceDao: SourceDao
) : SourceRepository {

    override fun getSource(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Flowable<List<SourcesResponse.Source>> {
        val network: Flowable<List<SourcesResponse.Source>> by lazy {
            mSourceRepository.getSource(params, page, pageSize)
        }


        return mSourceDao
            .sourceIsEmpty
            .flatMap { isEmpty ->
                if (isEmpty) {
                    network.flatMap { sourceList ->
                        mSourceDao.insertAll(sourceList)
                        mSourceDao.getAllLimit(pageSize, page * pageSize)
                    }
                } else {
                    mSourceDao.getAllLimit(pageSize, page * pageSize)
                }
            }
    }
}