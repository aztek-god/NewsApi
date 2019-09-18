package com.sergei.news.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.ui.adapter.abstr.DiffUtilAdapter
import com.sergei.news.util.DiffUtilItem

inline fun RecyclerView.endOfListListener(
    backPressure: Long = 0,
    crossinline listener: (RecyclerViewDirection) -> Unit
) {
    val mBackPressure = BackPressure(backPressure)

    var direction: RecyclerViewDirection = RecyclerViewDirection.DOWN

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!recyclerView.canScrollVertically(1)) {
                mBackPressure.next(direction) {
                    listener(it)
                }
            } else {
                mBackPressure.next(direction) {
                    listener(it)
                }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            direction = if (dy >= 0) {
                RecyclerViewDirection.DOWN
            } else {
                RecyclerViewDirection.UP
            }
        }
    })
}


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

enum class RecyclerViewDirection {
    START, UP, DOWN
}

fun <T : DiffUtilItem, VH : RecyclerView.ViewHolder> DiffUtilAdapter<T, VH>.isBottomReached(
    layoutManager: LinearLayoutManager
): Boolean {
    return mDataBundle.size - 1 == layoutManager.findLastVisibleItemPosition()
}

inline fun endOfListListener(
    backPressure: Long = 0,
    crossinline predicate: () -> Boolean,
    crossinline listener: () -> Unit
) {
    val mBackPressure = BackPressure(backPressure)

    mBackPressure.next(Unit, {
        if (predicate()) {
            listener()
        }
    })
}

inline fun <T :DiffUtilItem, VH: RecyclerView.ViewHolder>RecyclerView.onEndReachedListener(
    backPressure: Long = 0,
    adapter: DiffUtilAdapter<T, VH>,
    layoutManager: LinearLayoutManager,
    crossinline onBottomListener: () -> Unit
) {

    var direction = RecyclerViewDirection.START
    val backPressure = BackPressure(backPressure)

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            direction = if (dy > 0) {
                logd("dy = $dy", "sdvskidex@mail.ruuu")
                RecyclerViewDirection.DOWN
            } else {
                logd("UP", "sdvskidex@mail.ruuu")
                RecyclerViewDirection.UP
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)


            if (direction == RecyclerViewDirection.DOWN && adapter.isBottomReached(layoutManager)) {
                backPressure.next(Unit, {
                    onBottomListener()
                })
            }

        }
    })
}