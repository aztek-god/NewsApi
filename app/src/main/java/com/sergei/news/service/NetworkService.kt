package com.sergei.news.service

import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.SourcesResponse
import com.sergei.news.model.TopHeadsResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkService {

    @GET("everything")
    fun getEverything(@QueryMap params: Map<String, String>): Flowable<Response<EverythingResponse>>

    @GET("top-headlines")
    fun getTopHeadlines(@QueryMap params: Map<String, String>): Flowable<Response<TopHeadsResponse>>

    @GET("sources")
    fun getSources(@QueryMap params: Map<String, String>): Flowable<Response<SourcesResponse>>

}