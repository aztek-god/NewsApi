package com.sergei.news.ui.adapter

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.R
import com.sergei.news.extension.inflate
import com.sergei.news.extension.otherwise
import com.sergei.news.extension.then
import com.sergei.news.ui.adapter.abstr.DiffUtilAdapter
import com.sergei.news.util.DiffUtilItem
import com.sergei.news.util.Payload
import kotlinx.android.synthetic.main.view_holder_home_item.view.*

class HomeAdapter : DiffUtilAdapter<TestItem, HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_holder_home_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mDataBundle[position], null)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

        payloads.map { it as Bundle }.forEach { holder.bind(mDataBundle[position], it) }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val idTv: TextView = view.idTv
        private val nameTv: TextView = view.nameTv
        private val salaryTv: TextView = view.salaryTv

        fun bind(model: TestItem, bundle: Bundle?) {
            (bundle == null) then {
                idTv.text = model.id.toString()
                nameTv.text = model.name
                salaryTv.text = model.salary.toString()
            } otherwise {
                bundle?.run {
                    containsKey("id") then {
                        idTv.text = getInt("id").toString()
                    }

                    containsKey("name") then {
                        nameTv.text = getString("name")
                    }

                    containsKey("salary") then {
                        salaryTv.text = getInt("salary").toString()
                    }
                }

                salaryTv.text = model.salary.toString()
            }
        }
    }
}

data class TestItem(val id: Int, val name: String, val salary: Int) : DiffUtilItem,
    Payload<TestItem> {

    override fun areItemsTheSame(item: Any): Boolean {
        return this.id == (item as TestItem).id
    }

    override fun toPayloadBundle(diffUtilItem: TestItem): Bundle {
        val bundle = Bundle()
        if (this != diffUtilItem) {
            with(this) {
                if (this.id != diffUtilItem.id) {
                    bundle.putInt("id", diffUtilItem.id)
                }
                if (this.name != diffUtilItem.name) {
                    bundle.putString("name", diffUtilItem.name)
                }
                if (this.salary != diffUtilItem.salary) {
                    bundle.putInt("salary", diffUtilItem.salary)
                }
            }
        }

        return bundle
    }
}

val testItemList: List<TestItem> = listOf(
    TestItem(1, "sergei", 200_000),
    TestItem(2, "sergei", 200_000),
    TestItem(3, "sergei", 200_000),
    TestItem(4, "sergei", 200_000),
    TestItem(5, "sergei", 200_000),
    TestItem(6, "sergei", 200_000)
)

