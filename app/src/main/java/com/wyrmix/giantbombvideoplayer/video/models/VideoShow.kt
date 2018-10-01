package com.wyrmix.giantbombvideoplayer.video.models

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "video_show", indices = [ Index(name = "show_id", value = ["id"]) ])
data class VideoShow(
        @ColumnInfo(name = "api_detail_url") @SerializedName("api_detail_url") var apiDetailUrl: String = "",
        @PrimaryKey @SerializedName("id") var id: Long = 0,
        @ColumnInfo(name = "title") @SerializedName("title") var title: String = "",
        @ColumnInfo(name = "position") @SerializedName("position") var position: Long = 0,
        @ColumnInfo(name = "site_detail_url") @SerializedName("site_detail_url") var siteDetailUrl: String = "",
        @Embedded @SerializedName("image") var image: Image = Image()
): Parcelable
