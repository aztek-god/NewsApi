package com.sergei.news.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sergei.news.R
import com.sergei.news.extension.addLifecycleObserver
import com.sergei.news.extension.logd
import com.sergei.news.ui.fragment.abstr.BaseFragment
import com.sergei.news.util.Result
import com.sergei.news.viewmodel.EverythingViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val mEverythingViewModel: EverythingViewModel by viewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun init(bundle: Bundle?) {
        addLifecycleObserver("HomeFragment")

        mEverythingViewModel.observableLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> {
                    logd("result = $it")
                }
            }
        })
    }
}

