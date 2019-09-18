package com.sergei.news.ui.adapter.abstr

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.util.DiffUtilItem

abstract class DiffUtilViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: DiffUtilItem)
}