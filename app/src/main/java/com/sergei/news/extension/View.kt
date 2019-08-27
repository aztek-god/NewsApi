package com.sergei.news.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToParent: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, this, attachToParent)
}