package com.sergei.news.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.R
import com.sergei.news.extension.inflate
import com.sergei.news.model.EverythingResponse
import com.sergei.news.ui.adapter.ArticleAdapter.ArticleViewHolder.Companion.LAYOUT_RES
import com.sergei.news.ui.adapter.abstr.DiffUtilAdapter
import com.sergei.news.ui.adapter.abstr.DiffUtilViewHolder
import com.sergei.news.util.DiffUtilItem
import kotlinx.android.synthetic.main.view_holder_article_item.view.*
import kotlinx.android.synthetic.main.view_holder_source_item.view.*

class ArticleAdapter :
    DiffUtilAdapter<DiffUtilItem, DiffUtilViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffUtilViewHolder {
        return ArticleViewHolder(parent.inflate(LAYOUT_RES))
    }

    override fun onBindViewHolder(holder: DiffUtilViewHolder, position: Int) {
        val model = mDataBundle[position]
        holder.bind(model)
    }

    class ArticleViewHolder(view: View) :
        DiffUtilViewHolder(view) {

        private val mArticleTitleTv: TextView = view.articleTitleTv


        override fun bind(model: DiffUtilItem) {
            (model as? EverythingResponse.Article)?.let { article ->
                mArticleTitleTv.text = article.title
            }
        }

        companion object {
            const val LAYOUT_RES = R.layout.view_holder_article_item
        }
    }
}