package com.kyleriedemann.giantbombvideoplayer.video.models

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

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