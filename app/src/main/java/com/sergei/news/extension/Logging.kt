package com.sergei.news.extension

import android.util.Log

fun logv(message: String, tag: String = "") {
    Log.v(tag, message)
}

fun logd(message: String, tag: String = "") {
    Log.d(tag, message)
}

fun loge(message: String, tag: String = "") {
    Log.e(tag, message)
}

fun logi(message: String, tag: String = "") {
    Log.i(tag, message)
}

fun logw(message: String, tag: String = "") {
    Log.w(tag, message)
}

fun logWtf(message: String, tag: String = "") {
    Log.wtf(tag, message)
}
