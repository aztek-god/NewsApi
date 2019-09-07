package com.sergei.news.ui.fragment.util


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sergei.news.R
import com.sergei.news.ui.fragment.abstr.BaseFragment
import com.sergei.news.ui.fragment.abstr.Loggable

/**
 * A simple [Fragment] subclass.
 */
class TestFragment : BaseFragment(), Loggable {

    override val layoutRes: Int
        get() = R.layout.fragment_test


    override fun init(bundle: Bundle?) {
    }

}
