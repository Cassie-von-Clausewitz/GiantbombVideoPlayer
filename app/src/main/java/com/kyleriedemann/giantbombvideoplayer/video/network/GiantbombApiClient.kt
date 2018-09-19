package com.kyleriedemann.giantbombvideoplayer.video.network

import com.kyleriedemann.giantbombvideoplayer.video.models.Key
import com.kyleriedemann.giantbombvideoplayer.video.models.Result
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiantbombApiClient {

    @GET("videos/")
    fun getVideos(
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Deferred<Result>

    @GET("videos/")
    fun getVideosCall(
            @Query("api_key") apiKey: String,
            @Query("format") format: String,
            @Query("offset") offset: Int
    ): Call<Result>

    @GET("validate")
    fun getApiKey(
            @Query("link_code") linkCode: String,
            @Query("format") format: String
    ): Deferred<Key>
}
