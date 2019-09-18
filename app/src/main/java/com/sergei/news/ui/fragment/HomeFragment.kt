package com.sergei.news.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.R
import com.sergei.news.extension.*
import com.sergei.news.ui.adapter.ArticleAdapter
import com.sergei.news.ui.adapter.HomeAdapter
import com.sergei.news.ui.fragment.abstr.FrameFragment
import com.sergei.news.ui.fragment.util.LoadingFragment
import com.sergei.news.util.Result
import com.sergei.news.viewmodel.SourceEverythingViewModel
import com.sergei.news.viewmodel.SourcesViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : FrameFragment() {

    private val mSourceEverythingViewModel: SourceEverythingViewModel by viewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun init(bundle: Bundle?) {
        addLifecycleObserver("HomeFragment")


        with(homeRecyclerView) {
            val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            layoutManager = linearLayoutManager
            val homeAdapter = HomeAdapter()
            adapter = homeAdapter

            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

            onBottomOfListListener(400, homeAdapter, linearLayoutManager) {
                logd("")
            }
        }

        mSourceEverythingViewModel.observableLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    (homeRecyclerView.adapter as? HomeAdapter)?.let { adapter ->
                        adapter.update(it.data)
                    }
                }

                is Result.Progress -> {
                    if (it.isProgress) {
                        attachFragment(LoadingFragment())
                    } else {
                        delayAction(400) {
                            detachFragment(LoadingFragment::class.java)
                        }
                    }
                }
            }
        })
    }
}

