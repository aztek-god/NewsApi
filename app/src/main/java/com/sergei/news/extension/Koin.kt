package com.sergei.news.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sergei.news.util.Result
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel
import org.koin.android.viewmodel.ext.android.viewModel

inline fun <reified T : SingleLiveDataViewModel<T>> Fragment.observe(crossinline observer: (Result<T>) -> Unit) {
    return viewModel<T>().value.observableLiveData.observe(viewLifecycleOwner, Observer {
        observer(it)
    })
}

inline fun <reified T : SingleLiveDataViewModel<T>> Fragment.viewModel(): T {
    return viewModel<T>().value
}