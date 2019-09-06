package com.sergei.news.viewmodel.abstr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sergei.news.util.Result

abstract class SingleLiveDataViewModel<T> : DisposableViewModel() {

    private val mMutableLiveData: MutableLiveData<Result<T>> = MutableLiveData()

    val observableLiveData: LiveData<Result<T>> get() = mMutableLiveData

    fun outcomeResult(result: T) {
        mMutableLiveData.value = Result.createResult(result)
    }

    fun outcomeError(throwable: Throwable) {
        mMutableLiveData.value = Result.createError(throwable)
    }


    fun outcomeResult(isProgress: Boolean) {
        mMutableLiveData.value = Result.createProgress(isProgress)
    }
}