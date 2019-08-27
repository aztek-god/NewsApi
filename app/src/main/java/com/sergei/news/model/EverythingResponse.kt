package com.sergei.news.model


import com.google.gson.annotations.SerializedName
import com.sergei.news.util.DiffUtilItem

data class EverythingResponse(
    @SerializedName("articles")
    val articles: List<Article> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0
) : DiffUtilItem {
    data class Article(
        @SerializedName("author")
        val author: String = "",
        @SerializedName("content")
        val content: String = "",
        @SerializedName("description")
        val description: String = "",
        @SerializedName("publishedAt")
        val publishedAt: String = "",
        @SerializedName("source")
        val source: Source = Source(),
        @SerializedName("title")
        val title: String = "",
        @SerializedName("url")
        val url: String = "",
        @SerializedName("urlToImage")
        val urlToImage: String = ""
    ) : DiffUtilItem {
        data class Source(
            @SerializedName("id")
            val id: String = "",
            @SerializedName("name")
            val name: String = ""
        ) : DiffUtilItem
    }
}