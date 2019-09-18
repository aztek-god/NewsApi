package com.sergei.news.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sergei.news.model.SourcesResponse
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface SourceDao {

    @Query("SELECT * FROM source")
    fun getAll(): Flowable<List<SourcesResponse.Source>>

    @Query("SELECT * FROM source LIMIT :offset, :limit")
    fun getAllLimit(limit: Int, offset: Int = 0): Flowable<List<SourcesResponse.Source>>

    @Insert
    fun insertAll(sources: List<SourcesResponse.Source>)

    @Delete
    fun delete(sources: SourcesResponse.Source)

    @Query("SELECT count(*) FROM source")
    fun getCount(): Flowable<Int>
}

val SourceDao.sourceIsEmpty: Flowable<Boolean> get() = getCount().flatMap { Flowable.just(it == 0) }