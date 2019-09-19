package com.sergei.news.repository.combine

import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.util.EverythingSourceModel
import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.EverythingRepository
import com.sergei.news.repository.SourceEverythingRepository
import com.sergei.news.repository.SourceRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class RemoteSourceEverythingRepository(
    private val mSourceRepository: SourceRepository,
    private val mEverythingRepository: EverythingRepository
) : SourceEverythingRepository {

    override fun loadSourceEverything(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Flowable<List<EverythingSourceModel>> {

        return getSourceObservable(page, pageSize).concatMap { source: SourcesResponse.Source ->
            mEverythingRepository.getEverything(mapOf("sources" to source.id))
                .subscribeOn(Schedulers.io())
                .flatMap { articleList: List<EverythingResponse.Article> ->
                    Flowable.just(
                        EverythingSourceModel(
                            source,
                            articleList
                        )
                    )
                }
        }.buffer(pageSize)
    }


    private fun getSourceObservable(page: Int, pageSize: Int): Flowable<SourcesResponse.Source> {
        return mSourceRepository
            .getSource(mapOf(), page, pageSize)
            .flatMap {
                Flowable.fromIterable(it)
            }
    }
}
