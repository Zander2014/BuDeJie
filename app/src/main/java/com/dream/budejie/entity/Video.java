package com.dream.budejie.entity;

import java.util.List;

/**
 * Created by jerry on 2015/12/20.
 */
public class Video {
    private String playfcount;
    private String width;
    private String height;
    private List<String> video;
    private String duration;
    private String playcount;
    private List<String> thumbnail;
    private List<String> download;

    public Video() {
    }

    public List<String> getVideo() {
        return video;
    }

    public void setVideo(List<String> video) {
        this.video = video;
    }

    public List<String> getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(List<String> thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getDownload() {
        return download;
    }

    public void setDownload(List<String> download) {
        this.download = download;
    }

    public String getPlayfcount() {
        return playfcount;
    }

    public void setPlayfcount(String playfcount) {
        this.playfcount = playfcount;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }


}
