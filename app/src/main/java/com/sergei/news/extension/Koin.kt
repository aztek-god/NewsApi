package com.sergei.news.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.sergei.news.util.Outcome
import com.sergei.news.viewmodel.abstr.SingleLiveDataViewModel
import org.koin.android.viewmodel.ext.android.viewModel

inline fun <reified T : SingleLiveDataViewModel<T>> Fragment.observableLiveData(): LiveData<Outcome<T>> {
    return viewModel<T>().value.observableLiveData
}