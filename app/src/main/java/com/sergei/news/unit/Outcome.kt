package com.sergei.news.unit

sealed class Outcome<T> {
    data class Result<T>(val data: T, val isLast: Boolean = false) : Outcome<T>()

    data class Error<T>(val throwable: Throwable) : Outcome<T>()

    class EmptyResult<T> : Outcome<T>()

    data class Progress<T>(val isProgress: Boolean) : Outcome<T>()
}