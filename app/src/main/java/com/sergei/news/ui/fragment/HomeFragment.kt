package com.sergei.news.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sergei.news.R
import com.sergei.news.extension.addLifecycleObserver
import com.sergei.news.extension.observableLiveData
import com.sergei.news.ui.fragment.abstr.BaseFragment
import com.sergei.news.util.Outcome
import com.sergei.news.viewmodel.EverythingViewModel

class HomeFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun init(bundle: Bundle?) {
        addLifecycleObserver("HomeFragment")

        observableLiveData<EverythingViewModel>().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Outcome.Result -> {
                    // empty
                }
            }
        })
    }
}

