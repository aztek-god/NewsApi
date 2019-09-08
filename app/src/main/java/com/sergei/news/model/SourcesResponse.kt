package com.sergei.news.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sergei.news.util.DiffUtilItem

data class SourcesResponse(
    val id: Int,
    @SerializedName("sources")
    val sources: List<Source> = listOf(),
    @SerializedName("status")
    val status: String = ""
) : DiffUtilItem {
    @Entity
    data class Source(
        @SerializedName("category")
        val category: String = "",
        @SerializedName("country")
        val country: String = "",
        @SerializedName("description")
        val description: String = "",
        @PrimaryKey
        @SerializedName("id")
        val id: String = "",
        @SerializedName("language")
        val language: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("url")
        val url: String = ""
    ) : DiffUtilItem
}