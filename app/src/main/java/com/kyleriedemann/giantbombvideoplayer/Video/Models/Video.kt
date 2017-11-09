package com.kyleriedemann.giantbombvideoplayer.Video.Models

import com.google.gson.annotations.SerializedName

data class Video(
        @SerializedName("api_detail_url") var apiDetailurl: String,
        @SerializedName("deck") var deck: String,
        @SerializedName("hd_url") var hdUrl: String,
        @SerializedName("high_url") var highUrl: String,
        @SerializedName("low_url") var lowUrl: String,
        @SerializedName("id") var id: Int = 0,
        @SerializedName("length_seconds") var lengthSeconds: Int = 0,
        @SerializedName("name") var name: String,
        @SerializedName("publish_date") var publishDate: String,
        @SerializedName("site_detail_url") var siteDetailUrl: String,
        @SerializedName("url") var ulr: String,
        @SerializedName("image") var videoImage: Image,
        @SerializedName("user") var user: String,
        @SerializedName("video_type") var videoType: String,
        @SerializedName("youtube_id") var youtubeId: String
)
