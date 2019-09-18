package com.sergei.news.ui.adapter

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.R
import com.sergei.news.extension.inflate
import com.sergei.news.model.SourcesResponse
import com.sergei.news.ui.adapter.abstr.DiffUtilAdapter
import kotlinx.android.synthetic.main.view_holder_home_item.view.*

class HomeAdapter : DiffUtilAdapter<SourcesResponse.Source, HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_holder_home_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mDataBundle[position], null)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val sourceName: TextView = view.vhSourceNameTv

        fun bind(model: SourcesResponse.Source, bundle: Bundle?) {
            with(model) {
                sourceName.text = name
            }
        }
    }
}

