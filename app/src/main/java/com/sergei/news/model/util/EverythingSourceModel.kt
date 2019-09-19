package com.sergei.news.model.util

import com.sergei.news.model.EverythingResponse
import com.sergei.news.model.SourcesResponse
import com.sergei.news.util.DiffUtilItem

data class EverythingSourceModel(
    val source: SourcesResponse.Source,
    val articles: List<EverythingResponse.Article>
): DiffUtilItem {
    val id: String get() = source.id
}