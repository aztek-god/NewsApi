package com.sergei.news.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sergei.news.util.livedata.LifecycleLoggerObserver

fun FragmentManager.transaction(
    allowStateLoss: Boolean = false,
    transaction: FragmentTransaction.() -> Unit
) {
    val currentTransaction = beginTransaction()
    currentTransaction.transaction()
    if (!allowStateLoss) {
        currentTransaction.commit()
    } else {
        currentTransaction.commitAllowingStateLoss()
    }
}

fun Fragment.addLifecycleObserver(tag: String) {
    lifecycle.addObserver(LifecycleLoggerObserver(tag))
}