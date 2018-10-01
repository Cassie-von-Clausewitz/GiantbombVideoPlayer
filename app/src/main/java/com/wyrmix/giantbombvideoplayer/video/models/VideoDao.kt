package com.wyrmix.giantbombvideoplayer.video.models

import androidx.room.*

@Dao
interface VideoDao {
    @Query("SELECT * FROM video")
    fun selectAll(): List<Video>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideo(vararg video: Video): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun UpdateVideo(vararg video: Video): Int

    @Delete
    fun deleteVideo(video: Video)
}