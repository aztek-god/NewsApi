package com.sergei.news.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.R
import com.sergei.news.extension.inflate
import com.sergei.news.model.util.BottomProgress
import com.sergei.news.model.util.EverythingSourceModel
import com.sergei.news.ui.adapter.abstr.DiffUtilAdapter
import com.sergei.news.ui.adapter.abstr.DiffUtilViewHolder
import com.sergei.news.util.DiffUtilItem
import com.sergei.news.util.exception.UnsupportedViewTypeException
import kotlinx.android.synthetic.main.view_holder_source_item.view.*

class HomeAdapter() :
    DiffUtilAdapter<DiffUtilItem, DiffUtilViewHolder>() {

    private val mViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffUtilViewHolder {
        return when (viewType) {
            SourceArticleViewHolder.VIEW_TYPE -> SourceArticleViewHolder(
                parent.inflate(R.layout.view_holder_source_item),
                mViewPool
            )

            BottomProgressBarViewHolder.VIEW_TYPE -> BottomProgressBarViewHolder(parent.inflate(R.layout.view_holder_progress_bar))

            else -> throw UnsupportedViewTypeException()
        }
    }

    override fun onBindViewHolder(holder: DiffUtilViewHolder, position: Int) {
        holder.bind(mDataBundle[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (mDataBundle[position]) {
            is EverythingSourceModel -> SourceArticleViewHolder.VIEW_TYPE
            is BottomProgress -> BottomProgressBarViewHolder.VIEW_TYPE
            else -> throw UnsupportedViewTypeException()
        }
    }

    fun appendData(dataList: List<DiffUtilItem>) {
        val mutableBundle = mDataBundle.toMutableList()
        mutableBundle.removeIf {
            it is BottomProgress
        }
        update(mutableBundle + dataList)
    }

    class SourceArticleViewHolder(
        view: View,
        viewPool: RecyclerView.RecycledViewPool
    ) :
        DiffUtilViewHolder(view) {

        private val sourceName: TextView = view.vhSourceNameTv

        private val mArticleRv: RecyclerView = view.articleRv.apply {
            layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            setRecycledViewPool(viewPool)
            adapter = ArticleAdapter()
        }

        override fun bind(model: DiffUtilItem) {
            (model as? EverythingSourceModel)?.let { obj ->
                sourceName.text = obj.source.name
                (mArticleRv.adapter as? ArticleAdapter)?.update(obj.articles)
            }
        }

        companion object {
            const val VIEW_TYPE = 1
        }
    }

    class BottomProgressBarViewHolder(view: View) : DiffUtilViewHolder(view) {

        override fun bind(model: DiffUtilItem) {
            // empty
        }

        companion object {
            const val VIEW_TYPE = 2
        }
    }

}

