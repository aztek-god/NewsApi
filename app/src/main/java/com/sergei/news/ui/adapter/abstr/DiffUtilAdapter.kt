package com.sergei.news.ui.adapter.abstr

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sergei.news.util.DiffUtilItem
import com.sergei.news.util.Payload

abstract class DiffUtilAdapter<T : DiffUtilItem, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    var mDataBundle: List<T> = listOf()
        private set

    fun update(newDataList: List<T>) {
        val result = DiffUtil.calculateDiff(
            DiffUtilCallback(
                mDataBundle,
                newDataList
            )
        )

        mDataBundle = newDataList
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = mDataBundle.size

    private class DiffUtilCallback<T : DiffUtilItem>(
        private val mOldData: List<T>,
        private val mNewData: List<T>
    ) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldData[oldItemPosition].areItemsTheSame(mNewData[newItemPosition])
        }

        override fun getOldListSize() = mOldData.size

        override fun getNewListSize() = mNewData.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldData[oldItemPosition].areContentTheSame(mNewData[newItemPosition])
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val oldItem = mOldData[oldItemPosition]
            val newItem = mNewData[newItemPosition]

            val oldPayload: Payload<T>? = oldItem as? Payload<T>

            if (oldPayload != null) {
                return oldPayload.toPayloadBundle(newItem)
            }

            return super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}