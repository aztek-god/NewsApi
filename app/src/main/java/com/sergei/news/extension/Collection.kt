package com.sergei.news.extension

inline fun <K, V> Map<K, V>.iterate(action: (K, V) -> Unit) {
    for ((key, value) in this) {
        action(key, value)
    }
}

fun <K, V> Map<K, List<V>>.mergeList(): List<V> {
    val mutableList = mutableListOf<V>()

    for ((_, value) in this) {
        mutableList.addAll(value)
    }

    return mutableList
}