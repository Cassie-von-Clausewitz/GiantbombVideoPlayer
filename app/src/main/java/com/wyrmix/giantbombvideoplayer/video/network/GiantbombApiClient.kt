package com.wyrmix.giantbombvideoplayer.video.network

import com.wyrmix.giantbombvideoplayer.video.models.Key
import com.wyrmix.giantbombvideoplayer.video.models.VideoCategoryResult
import com.wyrmix.giantbombvideoplayer.video.models.VideoResult
import com.wyrmix.giantbombvideoplayer.video.models.VideoShowResult
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiantbombApiClient {

    @GET("videos/")
    fun getVideos(
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Deferred<VideoResult>

    @GET("videos/")
    fun getVideosCall(
            @Query("api_key") apiKey: String,
            @Query("format") format: String,
            @Query("offset") offset: Int
    ): Call<VideoResult>

    @GET("video_shows/")
    fun getVideoShows(
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Deferred<VideoShowResult>

    @GET("video_categories/")
    fun getVideoCategories(
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Deferred<VideoCategoryResult>

    @GET("validate")
    fun getApiKey(
            @Query("link_code") linkCode: String,
            @Query("format") format: String
    ): Deferred<Key>
}
