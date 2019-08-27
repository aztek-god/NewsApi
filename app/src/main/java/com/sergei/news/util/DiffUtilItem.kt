package com.sergei.news.util

interface DiffUtilItem {
    fun areItemsTheSame(item: Any) = this == item

    fun areContentTheSame(item: Any) = this == item
}