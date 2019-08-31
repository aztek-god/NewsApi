package com.sergei.news.ui.fragment

import android.os.Bundle
import com.sergei.news.R
import com.sergei.news.extension.addLifecycleObserver
import com.sergei.news.extension.observe
import com.sergei.news.extension.viewModel
import com.sergei.news.ui.fragment.abstr.BaseFragment
import com.sergei.news.viewmodel.EverythingViewModel

class HomeFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun init(bundle: Bundle?) {
        addLifecycleObserver("HomeFragment")

        observe<EverythingViewModel> {

        }

        viewModel<EverythingViewModel>().doAnything()
    }
}

