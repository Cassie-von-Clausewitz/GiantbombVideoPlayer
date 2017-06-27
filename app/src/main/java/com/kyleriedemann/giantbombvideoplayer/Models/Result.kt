package com.kyleriedemann.giantbombvideoplayer.Models

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("results") var results: List<Video>
)
