package com.sergei.news.viewmodel.abstr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sergei.news.util.Result

abstract class SingleLiveDataViewModel<T> : DisposableViewModel() {

    private val mMutableLiveData: MutableLiveData<Result<T>> = MutableLiveData()

    val observableLiveData: LiveData<Result<T>> get() = mMutableLiveData

    fun outcomeSuccess(result: T) {
        mMutableLiveData.value = Result.createSuccess(result)
    }

    fun outcomeFailure(throwable: Throwable) {
        mMutableLiveData.value = Result.createError(throwable)
    }


    fun outcomeProgress(isProgress: Boolean) {
        mMutableLiveData.postValue(Result.createProgress(isProgress))
    }
}