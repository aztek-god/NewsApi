package com.sergei.news.viewmodel

import com.sergei.news.model.TopHeadsResponse
import com.sergei.news.service.NetworkService
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel

class TopHeadsViewModel(private val mNetworkService: NetworkService) : SingleLiveDataViewModel<TopHeadsResponse>() {
}