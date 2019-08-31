package com.sergei.news.extension

inline infix fun <T> Boolean?.then(action: () -> T): T? {
    return if (this == true) {
        action()
    } else {
        null
    }
}

inline infix fun <T> T?.otherwise(action: () -> T): T {
    return this ?: action()
}