package com.sergei.news.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.ui.adapter.abstr.DiffUtilAdapter
import com.sergei.news.util.DiffUtilItem


class BackPressure(private val offsetInterval: Long) {
    private var isBlocked = false

    private var lastTime: Long = 0

    fun <T> next(value: T, action: (T) -> Unit) {
        if (!isBlocked) {
            isBlocked = true
            action(value)
            lastTime = currentTimeMillis
        }

        if (currentTimeMillis > lastTime + offsetInterval) {
            isBlocked = false
        }
    }
}

val currentTimeMillis: Long get() = System.currentTimeMillis()

val threadName: String get() = Thread.currentThread().name


fun RecyclerView.onBottomOfListListener(
    backpressureTime: Long,
    adapter: RecyclerView.Adapter<*>,
    layoutManager: LinearLayoutManager,
    listener: () -> Unit
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {

        private val backpressure = BackPressure(backpressureTime)

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if ((layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) && dy > 0) {
                backpressure.next(Unit) {
                    listener()
                }
            }
        }
    })
}