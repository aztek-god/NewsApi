package com.sergei.news.repository.combine

import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.EverythingSourceModel
import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.SourceEverythingRepository
import io.reactivex.Flowable
import java.lang.ref.Reference
import java.lang.ref.SoftReference

class BufferedSourceEverythingRepository(private val mSourceEverythingRepository: SourceEverythingRepository) :
    SourceEverythingRepository {

    private var mCacheReference: Reference<List<EverythingSourceModel>> =
        SoftReference(null)

    override fun loadSourceEverything(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Flowable<List<EverythingSourceModel>> {
        val result = mCacheReference.get()

        return if (result == null) {
            mSourceEverythingRepository
                .loadSourceEverything(params, page, pageSize)
                .doOnNext {
                    mCacheReference = SoftReference(it)
                }
        } else {
            Flowable.just(result)
        }
    }
}
