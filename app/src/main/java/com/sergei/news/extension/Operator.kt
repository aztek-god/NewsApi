package com.sergei.news.extension

inline infix fun Boolean?.forward(action: () -> Unit) {
    if (this == true) {
        action()
    }
}