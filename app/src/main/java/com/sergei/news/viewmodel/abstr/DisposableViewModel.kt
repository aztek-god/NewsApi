package com.sergei.news.viewmodel.abstr

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class DisposableViewModel : ViewModel() {

    private val mCompositeDisposable: CompositeDisposable by lazy(::CompositeDisposable)

    fun addDisposable(disposable: Disposable) {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.add(disposable)
        }
    }

    override fun onCleared() {
        super.onCleared()

        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
    }
}