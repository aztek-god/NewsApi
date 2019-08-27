package com.sergei.news.viewmodel.abstr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sergei.news.util.Outcome

abstract class SingleLiveDataViewModel<T> : DisposableViewModel() {

    private val mMutableLiveData: MutableLiveData<Outcome<T>> = MutableLiveData()

    val observableLiveData: LiveData<Outcome<T>> get() = mMutableLiveData

    fun outcomeResult(result: T) {
        mMutableLiveData.value = Outcome.createResult(result)
    }

    fun outcomeError(throwable: Throwable) {
        mMutableLiveData.value = Outcome.createError(throwable)
    }

    fun outcomeEmptyResult() {
        mMutableLiveData.value = Outcome.createEmptyResult()
    }

    fun outcomeResult(isProgress: Boolean) {
        mMutableLiveData.value = Outcome.createProgress(isProgress)
    }
}