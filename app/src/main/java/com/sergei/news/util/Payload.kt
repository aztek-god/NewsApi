package com.sergei.news.util

import android.os.Bundle

interface Payload<T : DiffUtilItem> {
    fun toPayloadBundle(diffUtilItem: T): Bundle
}