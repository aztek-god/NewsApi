package com.sergei.news.util

import com.sergei.news.extension.logd

interface DiffUtilItem {

    fun areItemsTheSame(item: Any) = this == item

    fun areContentTheSame(item: Any) = this == item
}