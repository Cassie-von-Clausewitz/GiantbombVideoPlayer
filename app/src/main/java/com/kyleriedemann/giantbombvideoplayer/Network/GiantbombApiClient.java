package com.kyleriedemann.giantbombvideoplayer.Network;

import com.kyleriedemann.giantbombvideoplayer.Models.Key;
import com.kyleriedemann.giantbombvideoplayer.Models.Result;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kyle on 3/31/15.
 */
public interface GiantbombApiClient {

    @GET("videos/")
    Observable<Result> getVideos(
            @Query("api_key") String apiKey,
            @Query("format") String format
    );

    @GET("validate/")
    Observable<Key> getApiKey(
            @Query("link_code") String linkCode,
            @Query("format") String format
    );
}
