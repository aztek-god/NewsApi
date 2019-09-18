package com.sergei.news.model

import com.sergei.news.util.DiffUtilItem

data class EverythingSourceModel(
    val source: SourcesResponse.Source,
    val articles: List<EverythingResponse.Article>
): DiffUtilItem