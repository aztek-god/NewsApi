package com.sergei.news.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.R
import com.sergei.news.extension.addLifecycleObserver
import com.sergei.news.ui.adapter.HomeAdapter
import com.sergei.news.ui.adapter.TestItem
import com.sergei.news.ui.adapter.testItemList
import com.sergei.news.ui.fragment.abstr.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private var flag = false

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun init(bundle: Bundle?) {
        addLifecycleObserver("HomeFragment")

        with(homeRecyclerView) {
            adapter = HomeAdapter().apply {
                update(
                    testItemList
                )
            }

            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        homeButton.setOnClickListener {
            if (flag) {
                (homeRecyclerView.adapter as HomeAdapter).update(testItemList)
            } else {
                (homeRecyclerView.adapter as HomeAdapter).update(testItemList2)
            }

            flag = !flag
        }
    }
}

val testItemList2: List<TestItem> = listOf(
    TestItem(1, "sergei-7", 200_000),
    TestItem(2, "sergei-8", 200_000),
    TestItem(3, "sergei-11", 200_000),
    TestItem(4, "sergei", 200_000),
    TestItem(5, "sergei", 200_000),
    TestItem(6, "sergei", 200_000)
)

