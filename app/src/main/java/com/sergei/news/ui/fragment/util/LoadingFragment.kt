package com.sergei.news.ui.fragment.util


import android.os.Bundle
import com.sergei.news.R
import com.sergei.news.extension.logd
import com.sergei.news.ui.fragment.abstr.BaseFragment
import com.sergei.news.ui.fragment.abstr.Loggable

class LoadingFragment : BaseFragment(), Loggable {

    override val layoutRes: Int
        get() = R.layout.fragment_loading

    override fun init(bundle: Bundle?) {
        // empty
    }

    override fun onDestroyView() {
        super.onDestroyView()

        logd("")
    }

    override fun onDestroy() {
        super.onDestroy()

        logd("")
    }
}
