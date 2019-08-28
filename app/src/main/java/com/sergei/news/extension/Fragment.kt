package com.sergei.news.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sergei.news.util.livedata.LifecycleLoggerObserver

fun FragmentManager.transaction(transaction: FragmentTransaction.() -> Unit) {
    val currentTransaction = beginTransaction()
    currentTransaction.transaction()
    currentTransaction.commit()
}

fun Fragment.addLifecycleObserver(tag: String) {
    lifecycle.addObserver(LifecycleLoggerObserver(tag))
}