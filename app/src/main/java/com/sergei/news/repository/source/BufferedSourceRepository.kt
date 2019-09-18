package com.sergei.news.repository.source

import com.sergei.news.model.SourcesResponse
import com.sergei.news.repository.SourceRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.lang.ref.Reference
import java.lang.ref.SoftReference

class BufferedSourceRepository(private val mSourceRepository: SourceRepository) : SourceRepository {

    private var mSoftReferenceStorage: Reference<List<SourcesResponse.Source>> =
        SoftReference(null)

    override fun getSource(
        params: Map<String, String>,
        page: Int,
        pageSize: Int
    ): Flowable<List<SourcesResponse.Source>> {

        val sourceList = mSoftReferenceStorage.get()

        return if (sourceList == null) {
            mSourceRepository
                .getSource(params, page, pageSize)
                .doOnNext {
                    mSoftReferenceStorage = SoftReference(it)
                }
        } else {
            Flowable.just(sourceList)
        }
    }
}