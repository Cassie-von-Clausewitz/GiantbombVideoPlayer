package com.kyleriedemann.giantbombvideoplayer.video.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
        entities = [
            Video::class
        ],
        version = 2,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao() : VideoDao
}