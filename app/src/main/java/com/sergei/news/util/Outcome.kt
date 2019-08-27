package com.sergei.news.util

sealed class Outcome<T> {
    data class Result<T>(val data: T, val isLast: Boolean = false) : Outcome<T>()

    data class Error<T>(val throwable: Throwable) : Outcome<T>()

    class EmptyResult<T> : Outcome<T>()

    data class Progress<T>(val isProgress: Boolean) : Outcome<T>()

    companion object {
        fun <T> createResult(result: T): Result<T> {
            return Result(result)
        }

        fun <T> createError(throwable: Throwable): Error<T> {
            return Error(throwable)
        }

        fun <T> createEmptyResult(): EmptyResult<T> {
            return EmptyResult()
        }

        fun <T> createProgress(isProgress: Boolean): Progress<T> {
            return Progress(isProgress)
        }
    }
}