package com.sergei.news.ui.fragment

import android.os.Bundle
import com.sergei.news.R
import com.sergei.news.extension.addLifecycleObserver
import com.sergei.news.ui.fragment.abstr.BaseFragment
import com.sergei.news.viewmodel.EverythingViewModel
import com.sergei.news.viewmodel.SourcesViewModel
import com.sergei.news.viewmodel.TopHeadsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val everythingViewModel: EverythingViewModel by viewModel()
    private val sourcesViewModel: SourcesViewModel by viewModel()
    private val topHeadsViewModel: TopHeadsViewModel by viewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun init(bundle: Bundle?) {
        addLifecycleObserver("sdvskidex::HomeFragment")
    }
}

