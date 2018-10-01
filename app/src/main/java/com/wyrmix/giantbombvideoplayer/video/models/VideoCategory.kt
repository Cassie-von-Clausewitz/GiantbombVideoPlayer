package com.wyrmix.giantbombvideoplayer.video.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "video_category", indices = [ Index(name = "category_id", value = ["id"]) ])
data class VideoCategory(
        @ColumnInfo(name = "api_detail_url") @SerializedName("api_detail_url") var apiDetailUrl: String,
        @PrimaryKey @SerializedName("id") var id: Long = 0,
        @ColumnInfo(name = "name") @SerializedName("name") var title: String,
        @ColumnInfo(name = "site_detail_url") @SerializedName("site_detail_url") var siteDetailUrl: String
): Parcelable
