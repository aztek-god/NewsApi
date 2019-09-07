package com.sergei.news.ui.fragment.abstr

interface Loggable {
    val className: String? get() = this::class.java.canonicalName
}