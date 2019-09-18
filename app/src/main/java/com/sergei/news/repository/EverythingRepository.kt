package com.sergei.news.repository

import com.sergei.news.model.EverythingResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface EverythingRepository {
    fun getEverything(params: Map<String, String>): Flowable<List<EverythingResponse.Article>>
}