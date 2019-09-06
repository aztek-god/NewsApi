package com.sergei.news.ui.fragment

import android.os.Bundle
import com.sergei.news.R
import com.sergei.news.extension.addLifecycleObserver
import com.sergei.news.ui.fragment.abstr.BaseFragment

class HomeFragment : BaseFragment() {

    private var flag = false

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun init(bundle: Bundle?) {
        addLifecycleObserver("HomeFragment")

    }
}

