package com.sergei.news.ui.adapter

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.R
import com.sergei.news.extension.inflate
import com.sergei.news.extension.otherwise
import com.sergei.news.extension.then
import kotlinx.android.synthetic.main.view_holder_home_item.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var mDataBundle: List<TestItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_holder_home_item))
    }

    override fun getItemCount(): Int {
        return mDataBundle.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mDataBundle[position], null)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

        repeat(payloads.map { it as Bundle }.size) { holder.bind(mDataBundle[position], null) }
    }

    fun update(newDataBundle: List<TestItem>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(mDataBundle, newDataBundle))
        mDataBundle = newDataBundle
        result.dispatchUpdatesTo(this)
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

data class TestItem(val id: Int, val name: String, val salary: Int)

val testItemList: List<TestItem> = listOf(
    TestItem(1, "sergei", 200_000),
    TestItem(2, "sergei", 200_000),
    TestItem(3, "sergei", 200_000),
    TestItem(4, "sergei", 200_000),
    TestItem(5, "sergei", 200_000),
    TestItem(6, "sergei", 200_000)
)

class DiffUtilCallback(private val oldList: List<TestItem>, private val newList: List<TestItem>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return oldList[oldItemPosition].toPayloadItem(newList[newItemPosition])
    }
}

fun TestItem.toBundle(): Bundle = Bundle().apply {
    putInt("id", id)
    putString("name", name)
    putInt("salary", salary)
}


infix fun TestItem.toPayloadItem(testItem: TestItem): Bundle = Bundle().apply {
    if (this@toPayloadItem != testItem) {
        with(this@toPayloadItem) {
            if (this.id != testItem.id) {
                this@apply.putInt("id", testItem.id)
            }
            if (this.name != testItem.name) {
                this@apply.putString("name", testItem.name)
            }
            if (this.salary != testItem.salary) {
                this@apply.putInt("salary", testItem.salary)
            }
        }
    }
}