package com.kyleriedemann.giantbombvideoplayer.Network;

import com.kyleriedemann.giantbombvideoplayer.Models.Result;
import com.kyleriedemann.giantbombvideoplayer.Models.Video;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kyle on 3/31/15.
 */
public class GiantbombApiClient {
    public static final String BASE_URL = "http://www.giantbomb.com/api";

    public interface Videos {
        @GET("/videos")
        public Result getVideos(
                @Query("api_key") String apiKey,
                @Query("format") String format
        );

        @GET("/videos")
        public void getVideos(
                @Query("api_key") String apiKey,
                @Query("format") String format,
                Callback<Result> videoCallback
        );

    }

    public static RestAdapter buildRestAdapter(boolean debug) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(BASE_URL);
        if(debug) builder.setLogLevel(RestAdapter.LogLevel.FULL);
        return builder.build();
    }
}
