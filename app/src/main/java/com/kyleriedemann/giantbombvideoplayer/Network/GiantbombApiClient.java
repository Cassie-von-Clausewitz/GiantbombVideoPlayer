package com.kyleriedemann.giantbombvideoplayer.Network;

import com.kyleriedemann.giantbombvideoplayer.Models.Video;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kyle on 3/31/15.
 */
public class GiantbombApiClient {
    public static final String BASE_URL = "http://www.giantbomb.com/api";

    /*
    retrofit interface example

    public interface GitHubService {
        @GET("/users/{user}/repos")
        List<Repo> listRepos(@Path("user") String user);
    }

     */

    public interface Videos {
        @GET("/videos")
        public List<Video> getVideos(
                @Query("api_key") String apiKey,
                @Query("format") String format
        );
    }

    public static RestAdapter buildRestAdapter(boolean debug) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(BASE_URL);
        if(debug) builder.setLogLevel(RestAdapter.LogLevel.FULL);
        return builder.build();
    }
}
