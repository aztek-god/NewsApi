package com.sergei.news.extension

import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.ui.adapter.abstr.DiffUtilAdapter
import com.sergei.news.util.DiffUtilItem

inline fun <T : DiffUtilItem, VH : RecyclerView.ViewHolder> DiffUtilAdapter<T, VH>.notifyCellBy(predicate: (T) -> Boolean) {
    mDataBundle.find(predicate)?.let {
        val index = mDataBundle.indexOf(it)
        notifyItemChanged(index)
    }
}