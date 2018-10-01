//package com.wyrmix.giantbombvideoplayer.video.models
//
//import android.os.Parcelable
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.ForeignKey
//import androidx.room.PrimaryKey
//import kotlinx.android.parcel.Parcelize
//
//@Parcelize
//@Entity(tableName = "video_join",
//        foreignKeys = [
//            ForeignKey(entity = Video::class, parentColumns = ["video_id"], childColumns = ["id"]),
//            ForeignKey(entity = VideoShow::class, parentColumns = ["video_show_id"], childColumns = ["id"]),
//            ForeignKey(entity = VideoCategory::class, parentColumns = ["video_category_id"], childColumns = ["id"])
//        ]
//)
//data class VideoJoin(
//        @PrimaryKey @ColumnInfo(name = "id") var videoJoinId: Long = 0,
//        @ColumnInfo(name = "video_id") var videoId: Long = 0,
//        @ColumnInfo(name = "video_show_id") var videoShowId: Long = 0,
//        @ColumnInfo(name = "video_category_id") var videoCategoryId: Long = 0
//): Parcelable
