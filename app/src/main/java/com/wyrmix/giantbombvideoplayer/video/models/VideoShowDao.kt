package com.wyrmix.giantbombvideoplayer.video.models

import androidx.room.*

@Dao interface VideoShowDao {
    @Query("SELECT * FROM video_show")
    fun selectAll(): List<VideoShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideoShow(vararg videoShow: VideoShow): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun UpdateVideoShow(vararg videoShow: VideoShow): Int

    @Delete
    fun deleteVideoShow(videoShow: VideoShow)
}