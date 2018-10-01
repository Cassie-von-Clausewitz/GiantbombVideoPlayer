package com.wyrmix.giantbombvideoplayer.video.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
        entities = [
//            VideoJoin::class,
            Video::class,
            VideoShow::class,
            VideoCategory::class
        ],
        version = 3,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao() : VideoDao
//    abstract fun videoJoinDao(): VideoJoinDao
    abstract fun videoShow(): VideoShowDao
    abstract fun videoCategory(): VideoCategoryDao
}