package com.sergei.news.ui.adapter

import android.provider.SyncStateContract.Helpers.update
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.R
import com.sergei.news.extension.inflate
import com.sergei.news.extension.logd
import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.EverythingSourceModel
import com.sergei.news.model.SourcesResponse
import com.sergei.news.ui.adapter.abstr.DiffUtilAdapter
import com.sergei.news.ui.adapter.abstr.DiffUtilViewHolder
import com.sergei.news.util.DiffUtilItem
import kotlinx.android.synthetic.main.view_holder_source_item.view.*

class HomeAdapter : DiffUtilAdapter<DiffUtilItem, DiffUtilViewHolder>() {

    private val mViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_holder_source_item), mViewPool)
    }

    override fun onBindViewHolder(holder: DiffUtilViewHolder, position: Int) {
        holder.bind(mDataBundle[position])
    }

    private val adapterMap: MutableMap<String, ArticleAdapter> = mutableMapOf()

    inner class ViewHolder(view: View, viewPool: RecyclerView.RecycledViewPool) :
        DiffUtilViewHolder(view) {

        private val sourceName: TextView = view.vhSourceNameTv


        private val mArticleRv: RecyclerView = view.articleRv.apply {
            layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            setRecycledViewPool(viewPool)
        }

        override fun bind(model: DiffUtilItem) {
            (model as? EverythingSourceModel)?.let { obj ->
                sourceName.text = obj.source.name

                mArticleRv.adapter = adapterMap.getOrPut(obj.id) {
                    ArticleAdapter().apply {
                        update(obj.articles)
                    }
                }

                logd("")
            }
        }
    }
}

