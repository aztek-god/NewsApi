package com.sergei.news.viewmodel

import com.sergei.news.service.NetworkService
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel

class SourcesViewModel(private val mNetworkService: NetworkService): SingleLiveDataViewModel<SourcesViewModel>() {
}