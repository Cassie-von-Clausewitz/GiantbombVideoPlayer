package com.kyleriedemann.giantbombvideoplayer.video.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
        @ColumnInfo(name = "icon_url") @SerializedName("icon_url") var iconUrl: String,
        @ColumnInfo(name = "medium_url") @SerializedName("medium_url") var mediumImageUrl: String,
        @ColumnInfo(name = "screen_url") @SerializedName("screen_url") var screenUrl: String,
        @ColumnInfo(name = "small_url") @SerializedName("small_url") var smallImageUrl: String,
        @ColumnInfo(name = "super_url") @SerializedName("super_url") var superImageUrl: String,
        @ColumnInfo(name = "thumb_url") @SerializedName("thumb_url") var thumbImageUrl: String,
        @ColumnInfo(name = "tiny_url") @SerializedName("tiny_url") var tinyImageUrl: String
): Parcelable
