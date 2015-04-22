package com.kyleriedemann.giantbombvideoplayer.Models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by kyle on 3/31/15.
 */
@Parcel
public class Video {

    @SerializedName("api_detail_url")
    String apiDetailurl;

    @SerializedName("deck")
    String deck;

    @SerializedName("hd_url")
    String hdUrl;

    @SerializedName("high_url")
    String highUrl;

    @SerializedName("low_url")
    String lowUrl;

    @SerializedName("id")
    int id;

    @SerializedName("length_seconds")
    int lengthSeconds;

    @SerializedName("name")
    String name;

    @SerializedName("publish_date")
    String publishDate;

    @SerializedName("site_detail_url")
    String siteDetailUrl;

    @SerializedName("url")
    String ulr;

    @SerializedName("image")
    Image videoImage;

    @SerializedName("user")
    String user;

    @SerializedName("video_type")
    String videoType;

    @SerializedName("youtube_id")
    String youtubeId;

    public Video() {
    }

    public String getApiDetailurl() {
        return apiDetailurl;
    }

    public void setApiDetailurl(String apiDetailurl) {
        this.apiDetailurl = apiDetailurl;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getHighUrl() {
        return highUrl;
    }

    public void setHighUrl(String highUrl) {
        this.highUrl = highUrl;
    }

    public String getLowUrl() {
        return lowUrl;
    }

    public void setLowUrl(String lowUrl) {
        this.lowUrl = lowUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLengthSeconds() {
        return lengthSeconds;
    }

    public void setLengthSeconds(int lengthSeconds) {
        this.lengthSeconds = lengthSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getUlr() {
        return ulr;
    }

    public void setUlr(String ulr) {
        this.ulr = ulr;
    }

    public Image getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(Image videoImage) {
        this.videoImage = videoImage;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    @Override
    public String toString() {
        return "Video{" +
                "apiDetailurl='" + apiDetailurl + '\'' +
                ", deck='" + deck + '\'' +
                ", hdUrl='" + hdUrl + '\'' +
                ", highUrl='" + highUrl + '\'' +
                ", lowUrl='" + lowUrl + '\'' +
                ", id=" + id +
                ", lengthSeconds=" + lengthSeconds +
                ", name='" + name + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", siteDetailUrl='" + siteDetailUrl + '\'' +
                ", ulr='" + ulr + '\'' +
                ", videoImage=" + videoImage +
                ", user='" + user + '\'' +
                ", videoType='" + videoType + '\'' +
                ", youtubeId='" + youtubeId + '\'' +
                '}';
    }
}
