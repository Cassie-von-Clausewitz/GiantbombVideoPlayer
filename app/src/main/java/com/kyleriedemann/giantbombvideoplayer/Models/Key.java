package com.kyleriedemann.giantbombvideoplayer.Models;

import com.google.gson.annotations.SerializedName;

public class Key {

    @SerializedName("api_key")
    String apiKey;

    public Key() {

    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return apiKey;
    }
}
