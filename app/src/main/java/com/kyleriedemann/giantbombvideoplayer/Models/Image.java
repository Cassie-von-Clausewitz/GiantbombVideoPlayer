package com.kyleriedemann.giantbombvideoplayer.Models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by kyle on 3/31/15.
 */
@Parcel
public class Image {

    @SerializedName("icon_url")
    String iconUrl;

    @SerializedName("medium_url")
    String mediumImageUrl;

    @SerializedName("screen_url")
    String screenUrl;

    @SerializedName("small_url")
    String smallImageUrl;

    @SerializedName("super_url")
    String superImageUrl;

    @SerializedName("thumb_url")
    String thumbImageUrl;

    @SerializedName("tiny_url")
    String tinyImageUrl;

    public Image(String iconUrl) {
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    public String getScreenUrl() {
        return screenUrl;
    }

    public void setScreenUrl(String screenUrl) {
        this.screenUrl = screenUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getSuperImageUrl() {
        return superImageUrl;
    }

    public void setSuperImageUrl(String superImageUrl) {
        this.superImageUrl = superImageUrl;
    }

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public void setThumbImageUrl(String thumbImageUrl) {
        this.thumbImageUrl = thumbImageUrl;
    }

    public String getTinyImageUrl() {
        return tinyImageUrl;
    }

    public void setTinyImageUrl(String tinyImageUrl) {
        this.tinyImageUrl = tinyImageUrl;
    }

    @Override
    public String toString() {
        return "Image{" +
                "iconUrl='" + iconUrl + '\'' +
                ", mediumImageUrl='" + mediumImageUrl + '\'' +
                ", screenUrl='" + screenUrl + '\'' +
                ", smallImageUrl='" + smallImageUrl + '\'' +
                ", superImageUrl='" + superImageUrl + '\'' +
                ", thumbImageUrl='" + thumbImageUrl + '\'' +
                ", tinyImageUrl='" + tinyImageUrl + '\'' +
                '}';
    }
}
