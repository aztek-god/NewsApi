package com.sergei.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sergei.news.model.SourcesResponse

@Database(entities = [SourcesResponse.Source::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sourceDao(): SourceDao
}