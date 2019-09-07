package com.sergei.news.util

sealed class Result<T> {
    data class Success<T>(val data: T, val isLast: Boolean = false, val isEmpty: Boolean = false) : Result<T>()

    data class Failure<T>(val throwable: Throwable) : Result<T>()

    data class Progress<T>(val isProgress: Boolean) : Result<T>()

    companion object {
        fun <T> createSuccess(result: T): Success<T> {
            return Success(result)
        }

        fun <T> createError(throwable: Throwable): Failure<T> {
            return Failure(throwable)
        }

        fun <T> createProgress(isProgress: Boolean): Progress<T> {
            return Progress(isProgress)
        }
    }
}