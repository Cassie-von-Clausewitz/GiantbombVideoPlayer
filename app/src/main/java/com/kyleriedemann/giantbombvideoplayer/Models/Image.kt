package com.kyleriedemann.giantbombvideoplayer.Models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("icon_url") var iconUrl: String,
    @SerializedName("medium_url") var mediumImageUrl: String,
    @SerializedName("screen_url") var screenUrl: String,
    @SerializedName("small_url") var smallImageUrl: String,
    @SerializedName("super_url") var superImageUrl: String,
    @SerializedName("thumb_url") var thumbImageUrl: String,
    @SerializedName("tiny_url") var tinyImageUrl: String
)
