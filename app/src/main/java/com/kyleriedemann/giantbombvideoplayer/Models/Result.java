package com.kyleriedemann.giantbombvideoplayer.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
