package com.sergei.news.extension

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToParent: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, this, attachToParent)
}

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

inline fun delayAction(delay: Number, crossinline action: () -> Unit) {
    Handler().postDelayed({ action() }, delay.toLong())
}
