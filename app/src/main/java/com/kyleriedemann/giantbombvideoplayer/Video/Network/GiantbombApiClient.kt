package com.kyleriedemann.giantbombvideoplayer.Video.Network

import com.kyleriedemann.giantbombvideoplayer.Video.Models.Key
import com.kyleriedemann.giantbombvideoplayer.Video.Models.Result

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiantbombApiClient {

    @GET("videos/")
    fun getVideos(
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Observable<Result>

    @GET("validate")
    fun getApiKey(
            @Query("link_code") linkCode: String,
            @Query("format") format: String
    ): Observable<Key>
}
