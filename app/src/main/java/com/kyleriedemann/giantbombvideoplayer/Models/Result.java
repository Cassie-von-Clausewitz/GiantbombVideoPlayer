package com.kyleriedemann.giantbombvideoplayer.Models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by kyle on 4/21/15.
 */
@Parcel
public class Result {

    @SerializedName("results")
    List<Video> results;

    public Result() {
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }
}
